SELECT type as category,count(type ) as count
FROM  (

SELECT 
  a.patchname ,a.prvpatch, count(distinct(a.device_id)) as device_count,pb.title,pb.vendor_info_page,
   a.patchtyp as type,
   paq.status,(CASE to_char(pt.iseverity)
            WHEN '1' THEN 'Critical'
            WHEN '2' THEN 'Important'
            WHEN '3' THEN 'Moderate'
            WHEN '4' THEN 'Low'
            Else 'Unknown'
        END) AS severity,pb.date_posted,pd.architecture , a.prodfamily,
   (CASE to_char(pfa.preference)
   WHEN '1' THEN 'Yes'
   Else 'No'
   END) AS preference,
   (CASE aq.state
   WHEN 'Success' THEN 'Yes'
   Else 'No'
   END) AS acqisition_status,a.bulletin,a.prodid,a.name,a.patchuid,a.vendor,a.sqnumber,a.language,l.lang_name,l.languagecode
FROM patchadv a left outer join DeviceConfig b on LOWER(a.device_id) = LOWER(b.device_id)
left join pa_product_detail pd on LOWER(a.prodid) = LOWER(pd.product_id)
left join pa_patch pt on LOWER(a.patchuid) = LOWER(pt.patch_uid)
left join (select state,patch_uid  from pa_patch_acq_details where state='Success' AND isDeleted <> '1' group by patch_uid,state) aq on LOWER(a.patchuid) = LOWER(aq.patch_uid)
left join pa_bulletin pb on LOWER(pt.bulletin_id) = LOWER(pb.bulletin_id)
left join (SELECT distinct region,languagecode,lang_name FROM pa_language Union SELECT '0','All','Default' FROM pa_language ) l on LOWER(a.language) = LOWER(l.region)
left join pa_family pfa on LOWER(pfa.family_name)= LOWER(a.prodfamily)
left join (SELECT patchname,vendor,bulletin,name,
					CASE WHEN rebootpending>0 THEN 'Reboot Pending' 
						  WHEN notpatched>0 THEN 'Not Patched'
						  ELSE 'Patched' END as status FROM (
						SELECT patchname,vendor,bulletin,name,
								 SUM(CASE WHEN LOWER(status)='patched' THEN 1 ELSE 0 END) AS patched,
								 SUM(CASE WHEN LOWER(status)='missing' THEN 1 ELSE 0 END) AS notpatched,
								 SUM(CASE WHEN LOWER(status)='reboot pending' THEN 1 ELSE 0 END) AS rebootpending
						FROM patchadv a LEFT JOIN DeviceConfig b on LOWER(a.device_id)= LOWER(b.device_id) GROUP BY patchname,vendor,bulletin,name )t
			 ) paq on LOWER(a.patchname)=LOWER(paq.patchname) and LOWER(a.name)=LOWER(paq.name) and LOWER(a.vendor)=LOWER(paq.vendor) and LOWER(a.bulletin)=LOWER(paq.bulletin)
group by a.name ,a.patchname,a.prvpatch,a.patchtyp,paq.status,pt.iseverity,pb.date_posted,pd.architecture,aq.state,preference,title,vendor_info_page,
a.bulletin,a.prodfamily,a.prodid,a.patchuid,a.vendor,a.sqnumber,a.language,l.lang_name,l.languagecode  ) t1 group by type