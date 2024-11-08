from flask import Blueprint, jsonify, request
from models import db, User, Product
from flask_jwt_extended import create_access_token, jwt_required, get_jwt_identity
import datetime

routes = Blueprint('routes', __name__)

@routes.route('/register', methods=['POST'])
def register():
    data = request.get_json()
    username = data.get('username')
    email = data.get('email')
    password = data.get('password')

    if User.query.filter_by(email=email).first():
        return jsonify({'message': 'User with this email already exists'}), 400

    user = User(username=username, email=email)
    user.password = password  # Используем свойство для установки пароля

    db.session.add(user)
    db.session.commit()

    return jsonify({'message': 'User registered successfully'}), 201

@routes.route('/login', methods=['POST'])
def login():
    data = request.get_json()
    email = data.get('email')
    password = data.get('password')

    user = User.query.filter_by(email=email).first()

    if user and user.verify_password(password):  # Используем метод для проверки пароля
        access_token = create_access_token(identity=user.id, expires_delta=datetime.timedelta(days=1))
        return jsonify({'access_token': access_token}), 200

    return jsonify({'message': 'Invalid credentials'}), 401

@routes.route('/products', methods=['GET'])
@jwt_required()
def get_products():
    current_user_id = get_jwt_identity()
    products = Product.query.filter_by(user_id=current_user_id).all()
    return jsonify([{
        "id": product.id,
        "name": product.name,
        "description": product.description,
        "price": product.price,
        "image_url": product.image_url,
        "category": product.category
    } for product in products]), 200

@routes.route('/products', methods=['POST'])
@jwt_required()
def add_product():
    current_user_id = get_jwt_identity()
    data = request.get_json()

    name = data.get('name')
    description = data.get('description')
    price = data.get('price')
    category = data.get('category')  # обязательный параметр
    image_url = data.get('image_url')  # не обязательный параметр

    if not name or not description or not price or not category:
        return jsonify({"message": "Missing required fields"}), 400

    product = Product(
        name=name,
        description=description,
        price=price,
        category=category,
        image_url=image_url,
        user_id=current_user_id
    )

    db.session.add(product)
    db.session.commit()

    return jsonify({'message': 'Product added successfully'}), 201


@routes.route('/account', methods=['GET', 'PUT'])
@jwt_required()
def account():
    current_user_id = get_jwt_identity()
    user = User.query.get(current_user_id)

    if request.method == 'GET':
        return jsonify({
            'username': user.username,
            'email': user.email
        })

    if request.method == 'PUT':
        data = request.get_json()
        user.username = data.get('username')
        user.email = data.get('email')
        db.session.commit()
        return jsonify({'message': 'Account updated successfully'})
