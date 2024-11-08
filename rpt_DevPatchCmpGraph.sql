select  status as category ,count(status) as count
	from ( select  case 
		when rebootpending>0 then 'Reboot Pending'
		when notpatched>0 then 'Not Patched'
		else 'Patched'
		end  as status, 
		device_id,
		mtime,
		product_count,
		patch_count,
		patched,
		notpatched,
		rebootpending,
		patch_count as total
	from (

SELECT 
		a.device_id AS device_id,  
		max(a.mtime) AS mtime,         
		(SELECT count( distinct name)
			FROM patchadv
			where LOWER(device_id)= LOWER(a.device_id) ) as product_count,
		   (SELECT count(name)
			FROM patchadv
			where LOWER(device_id)= LOWER(a.device_id) ) as patch_count,    
		(SELECT COUNT(status)
			FROM patchadv
			WHERE LOWER(status) ='patched'
			AND LOWER(device_id)= LOWER(a.device_id) ) as patched,
		(SELECT COUNT(status)
			FROM patchadv
			WHERE LOWER(status) ='missing'
			AND LOWER(device_id)= LOWER(a.device_id)  ) as notpatched,
		(SELECT COUNT(status)
			FROM patchadv
			WHERE LOWER(status) ='reboot pending'
			AND LOWER(device_id)= LOWER(a.device_id)  ) as rebootpending
FROM    patchadv a
LEFT OUTER JOIN 
        deviceconfig b
    ON LOWER(a.device_id) = LOWER(b.device_id)
	group by a.device_id
	
	) tb1 )tb2 group by status

	
