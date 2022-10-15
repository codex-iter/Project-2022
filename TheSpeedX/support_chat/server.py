import pymongo
import random
import uuid
import os
from datetime import datetime
from flask import Flask, request

app = Flask(__name__)

client = pymongo.MongoClient("mongodb+srv://XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX")

# connection=pymongo.MongoClient('localhost',27017)
database=client['schat']
collection=database['schatcoll']

def create(name,email):
	pin = str(uuid.uuid4())
	collection.insert_one({
		"_id":pin,
		"name":name,
		"email":email,
		"is_completed": False,
		"created_on": datetime.today().strftime('%Y-%m-%d'),
		"msg":[]
		})
	return {"status":True,"id":pin}

def update(pin,msg,close_session=False):
	collection.update_one({"_id":pin},{
		"$push":{"msg" :msg},
		"$set":{"is_completed":close_session}})
	
	return {"status":True}

def getMessage(pin):
	data=collection.find_one({"_id":pin})
	return data['msg']

def getByMail(mail):
	data=list(collection.find({"email":mail}))
	out=[{"session_id":d['_id'],"session_complete":d['is_completed'],"date":d['created_on']} for d in data]
	return {"status":True,"data":out}

@app.route("/create",methods=["POST"])
def api_create():
	name=request.get_json().get("name",None)
	email=request.get_json().get("email",None)
	try:
		if not name or not email:
			raise Exception
		return create(name,email)
	except:
		return {"status":False,"id":""}

@app.route("/update",methods=["POST"])
def api_update():
	id=request.get_json().get("id",None)
	msg=request.get_json().get("msg",None)
	is_query=request.get_json().get("is_query",True)
	close_session=request.get_json().get("close_session",False)
	try:
		if not id or not msg:
			raise Exception
		return update(id,{"text":msg,"stamp":str(datetime.now()),"is_query":is_query},close_session)
	except:
		return {"status":False}

@app.route("/fetch",methods=["POST"])
def fetch():
	id=request.get_json().get("id",None)
	try:
		if not id:
			raise Exception
		return {"status":True,"messages":getMessage(id)}
	except:
		return {"status":False}

@app.route("/list",methods=["POST"])
def list_data():
	mail=request.get_json().get("mail",None)
	try:
		if not mail:
			raise Exception
		return {"status":True,"messages":getByMail(mail)}
	except:
		return {"status":False}

# print(getByMail("x@mail.com"))
# print(create("X","x@mail.com"))
# print(update("ecc0938f-f070-48cf-b274-9464f2b61a95",{"text":"Hello World2","stamp":str(datetime.now()),"is_query":False}))
# print(update("ecc0938f-f070-48cf-b274-9464f2b61a95",{"text":"Hello World3","stamp":str(datetime.now()),"is_query":False},close_session=True))
# print(getMessage("61b128cc-b20a-42cb-94e9-311bfadc7b05"))
port = int(os.environ.get('PORT', 5000))
app.run(host='0.0.0.0',port=port,debug=False)
