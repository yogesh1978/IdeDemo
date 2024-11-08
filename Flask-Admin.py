from flask import Flask
from flask_sqlalchemy import SQLAlchemy
from flask_admin import Admin
from flask_admin.contrib.sqla import ModelView

app = Flask(__name__)
app.config['SQLALCHEMY_DATABASE_URI'] = 'sqlite:///your_database.db'
app.config['SECRET_KEY'] = 'your_secret_key'

db = SQLAlchemy(app)

# Определение вашей модели
class User(db.Model):
    id = db.Column(db.Integer, primary_key=True)
    username = db.Column(db.String(80), unique=True, nullable=False)
    email = db.Column(db.String(120), unique=True, nullable=False)

    def __repr__(self):
        return f'<User {self.username}>'

# Создание таблиц (если необходимо)
with app.app_context():
    db.create_all()

# Настройка Flask-Admin
admin = Admin(app, name='MyApp', template_mode='bootstrap3')
admin.add_view(ModelView(User, db.session))

if __name__ == '__main__':
    app.run(debug=True)
