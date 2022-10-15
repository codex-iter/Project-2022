from datetime import datetime
from flask import (
    Flask,
    render_template,
    request,
    redirect)
from flask_sqlalchemy import SQLAlchemy

app = Flask(__name__)
app.config['SQLALCHEMY_DATABASE_URI'] = 'sqlite:///bank.db'
app.config['SQLALCHEMY_TRACK_MODIFICATIONS'] = False
db = SQLAlchemy(app)


class User(db.Model):
    id = db.Column(db.Integer, primary_key=True)
    name = db.Column(db.String(128), nullable=False)
    email = db.Column(db.String(128), unique=True, nullable=False)
    balance = db.Column(db.Integer, nullable=False)

    def __repr__(self):
        return '<User %r>' % self.name


class Transaction(db.Model):
    id = db.Column(db.Integer, primary_key=True)
    sender_id = db.Column(db.Integer, db.ForeignKey('user.id'), nullable=False)
    receiver_id = db.Column(
        db.Integer, db.ForeignKey('user.id'), nullable=False)
    amount = db.Column(db.Integer, nullable=False)
    timestamp = db.Column(db.DateTime, nullable=False, default=datetime.utcnow)
    sender = db.relationship(User, foreign_keys='Transaction.sender_id')
    receiver = db.relationship(User, foreign_keys='Transaction.receiver_id')

    def __repr__(self):
        return '<Transaction %r>' % self.id


@app.route("/")
def home():
    return render_template("welcome.html")


@app.route("/dashboard")
def dashboard():
    users = User.query.all()
    return render_template("dashboard.html", users=users)


@app.route("/user/<user_id>")
def profile(user_id):
    user = User.query.filter_by(id=user_id).first_or_404()
    users = User.query.all()
    transactions = Transaction.query.filter(
        (Transaction.sender_id == user.id) |
        (Transaction.receiver_id == user.id)).order_by(
            Transaction.timestamp.desc()).all()
    return render_template(
        "profile.html",
        user=user,
        users=users,
        transactions=transactions
    )


@app.route("/logs")
def show_logs():
    transactions = Transaction.query.order_by(
        Transaction.timestamp.desc()).all()
    return render_template("transactions.html", transactions=transactions)


@app.route("/transact/<int:sender_id>", methods=["POST"])
def transact(sender_id):
    data = request.form
    receiver_id = int(data["receiver_id"])
    amount = int(data["amount"])
    sender = User.query.filter_by(id=sender_id).first()
    receiver = User.query.filter_by(id=receiver_id).first()
    if(sender.balance >= amount):
        sender.balance -= amount
        receiver.balance += amount
        db.session.add(
            Transaction(
                amount=amount,
                sender=sender,
                receiver=receiver
            )
        )
        db.session.commit()
    return redirect("/user/{id}".format(id=sender_id))


if __name__ == '__main__':
    db.create_all()
    app.run(host="0.0.0.0", threaded=True, port=5000)
