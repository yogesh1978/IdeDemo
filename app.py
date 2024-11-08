from flask import Flask
from flask_migrate import Migrate
from flask_jwt_extended import JWTManager
from flask_admin import Admin
from flask_admin.contrib.sqla import ModelView

from models import db, bcrypt, User, Product
from routes import routes
from config import Config


def create_app():
    app = Flask(__name__)
    app.config.from_object(Config)

    db.init_app(app)
    bcrypt.init_app(app)
    Migrate(app, db)
    JWTManager(app)

    # Регистрация административных представлений
    admin = Admin(app, name='Admin', template_mode='bootstrap3')
    admin.add_view(ModelView(User, db.session))
    admin.add_view(ModelView(Product, db.session))

    app.register_blueprint(routes)

    return app


# Создание экземпляра приложения
app = create_app()

if __name__ == '__main__':
    app.run(debug=True)

