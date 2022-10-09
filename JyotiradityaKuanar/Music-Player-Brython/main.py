	from browser import document,bind
	from browser.template import Template
	play=document["play"]
	pause=document["pause"]
	music=document["aud"]
	seek=document["seekbar"]
	img=document["music_jpg"]
	prev=document["prev"]
	next=document["next"]
	artist_name=document["artist_name"]
	music_list=[
		{
			"title" : 'Toofaan Title Track',
			"artist": '	Siddharth Mahadevan',
			"path" : "https://upload.wikimedia.org/wikipedia/en/7/71/Toofan_film_poster.jpg",
			"src" : "./02 - Toofaan Title Track.mp3",
		},
		{
			"title" : 'Sulthana',
			"artist": '	Sri Krishna & Prudhvi Chandra',
			"path" : "https://images.hungama.com/c/1/0b5/00a/85807330/85807330_300x300.jpg",
			"src" : "./Sulthana(PagalWorld).mp3",
		},
		{
			"title" : 'Toofan (KGF Chapter 2)',
			"artist": 'Brijesh Shandilya',
			"path" : "https://www.pagalvvorld.com/wp-content/uploads/2022/03/Toofan-Kgf-Chapter-2-Kannada-mp3-image.jpg",
			"src" : "./Toofan (KGF Chapter 2)(PagalWorldl).mp3",
		},
		{
			"title" : 'Once upon a Time',
			"artist": 'Anirudh Ravichander',
			"path" : "https://gallery.123telugu.com/content/slideshows/2020/09/Once-Upon-A-Time-There-Lived-A-Ghost/images/download.jpg",
			"src" : "./Once-Upon-A-Time-There-Lived-A-Ghost-mobringtone.net_.mp3",
		},
		{
			"title" : 'Porkanda Singam',
			"artist": 'Anirudh Ravichander,Ravi G',
			"path" : "https://pagalworldl.com/uploads/thumb/sft19/9118_4.jpg",
			"src" : "./Porkanda Singam Vikram 128 Kbps.mp3",
		},
		{
			"title" : 'Lokiverse Theme',
			"artist": 'Anirudh Ravichander',
			"path" : "https://i.ytimg.com/vi/23Ibm7sVTZs/maxresdefault.jpg",
			"src" : "./Lokiverse-Background-Score-Bgm-Ringtone.mp3",
		},
		{
			"title" : 'Gaaliyaan',
			"artist": 'Ankit Tiwari',
			"path": "./sp-thumbs/galliyan.jpg",
			"src": "./Music/01 - Galliyan - DownloadMing.SE.mp3",
		},
		{
			"title" : 'Gerua',
			"artist": 'Arijit Singh',
			"path": "./sp-thumbs/gerua.jpg",
			"src": "./Music/01 - Gerua - DownloadMing.SE.mp3",
		},
		{
			"title" : 'Ae Watan',
			"artist": 'Arijit Singh',
			"path": "./sp-thumbs/aewatan.jpg",
			"src": "./Music/01 - Raazi - Ae Watan [DJMaza.Fun].mp3",
		}
	]
	song=music_list[0]["title"]
	artist=music_list[0]["artist"]
	path=music_list[0]["path"]
	source=music_list[0]["src"]
	print(path)
	team=document["team"]
	team.innerHTML=song
	img.attrs["src"]=path
	music.attrs["src"]=source
	artist_name.innerHTML=artist
	index=0
	length=len(music_list)

	def playit(e):
		global music,play,pause,song
		music.play()
		play.classList.add("hidden")
		pause.classList.remove("hidden")
		

	def pauseit(e):
		global music,play,pause,song
		music.pause()
		
		pause.classList.add("hidden")
		play.classList.remove("hidden")
	
	def nextone(e):
		global index,length
		index+=1
		if index > length-1 :
			index=0
		team.innerHTML=music_list[index]["title"]
		img.attrs["src"]=music_list[index]["path"]
		music.attrs["src"]=music_list[index]["src"]
		artist_name.innerHTML=music_list[index]["artist"]
		music.play()
		changeduration(e)

	def prevone(e):
		global index,length
		index-=1
		if index < 0 :
			index=length-1
		team.innerHTML=music_list[index]["title"]
		img.attrs["src"]=music_list[index]["path"]
		music.attrs["src"]=music_list[index]["src"]
		artist_name.innerHTML=music_list[index]["artist"]
		music.play()
		changeduration(e)

	def changeduration(e):
		global music,seek
		#print(i)
		j=int(seek.value)
		sliderpos= (music.duration) * (j/100)
		music.currentTime = sliderpos
		

	def mainfunc(e):
		global music,seek
		seek.value=music.currentTime*(100/music.duration)
		
	play.bind('click',playit)
	pause.bind('click',pauseit)
	seek.bind('change',changeduration)
	music.bind("timeupdate",mainfunc)
	prev.bind('click', prevone)
	next.bind('click', nextone)
