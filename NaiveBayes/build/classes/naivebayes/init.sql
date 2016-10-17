

create database mdb;
use mdb;

create table boxofficeclass(
	ID int not null auto_increment, 
	min_value int not null, 
	max_value int not null,
	primary key(ID)
);

create table person(
	ID int not null auto_increment, 
	name varchar(30),
	primary key(ID)
);

create table genres(
	ID int not null auto_increment, 
	name varchar(30),
	primary key(ID)
);


create table movie_vgame(
	ID int not null auto_increment, 
	name varchar(200) not null, 
	ReleaseDate date, 
	boxoffice int, 
	boxofficeclass int,
        ratings float,
        budget int default 0,
        imgfile varchar(300),
	primary key(ID),
	foreign key(boxofficeclass)
		references boxofficeclass(ID)
);


create table personMovie(
	Mid int not null, 
	Pid int not null, 
	role varchar(30) not null,
	foreign key(Mid)
		references movie_vgame(ID),
	foreign key(Pid)
		references Person(ID),
	unique(Mid,Pid,role)
	
);

create table genreMovie(
	Mid int not null, 
	Gid int not null,
	foreign key(Mid)
		references movie_vgame(ID),
	foreign key(Gid)
		references Genres(ID),
	unique(Mid,Gid)

);


insert into genres(name) values('Romantic');
insert into genres(name) values('Comedy');
insert into genres(name) values('Thriller');
insert into genres(name) values('Mystery');
insert into genres(name) values('Crime');
insert into genres(name) values('Drama');
insert into genres(name) values('Historic');
insert into genres(name) values('Epic');
insert into genres(name) values('Action');


insert into person(name) values('Salman Khan');
insert into person(name) values('Shahrukh Khan');
insert into person(name) values('Aamir Khan');
insert into person(name) values('Hritik Roshan');
insert into person(name) values('Shahid Kapur');
insert into person(name) values('Ranvir Singh');
insert into person(name) values('Ranbir Kapoor');
insert into person(name) values('Arjun Kapoor');
insert into person(name) values('Sushant Sing Rajput');
insert into person(name) values('Ayushman Khurana');
insert into person(name) values('Saif Ali Khan');
insert into person(name) values('Akshay Kumar');
insert into person(name) values('Ritesh Deshmukh');
insert into person(name) values('Aditya Roy Kapur');
insert into person(name) values('Shraddha Kapoor');
insert into person(name) values('Katrina Kaif');
insert into person(name) values('Deepika Padukone');
insert into person(name) values('Pareeniti Chopra');
insert into person(name) values('Priyanka Chopra');
insert into person(name) values('Kareena Kapoor');
insert into person(name) values('Jacqueline Fernandis');
insert into person(name) values('Genelia Desouza');
insert into person(name) values('Anushka Sharma');
insert into person(name) values('Bhagyashree');
insert into person(name) values('Rani Mukherji');
insert into person(name) values('Asin');
insert into person(name) values('Soha Ali Khan');
insert into person(name) values('Kajol');
insert into person(name) values('Amisha Patel');
insert into person(name) values('Prity Zinta');
insert into person(name) values('Gracy Singh');
insert into person(name) values('Twinkle Khanna');
insert into person(name) values('Manisha Koirala');
insert into person(name) values('Sonali Bendre');
insert into person(name) values('Kareeshma Kapoor');
insert into person(name) values('Juhi Chawla');
insert into person(name) values('Laara Dutta');
insert into person(name) values('Daisy Shah');
insert into person(name) values('Sonakshi');
insert into person(name) values('Zarine Khan');
insert into person(name) values('Ayesha Takia');


insert into boxofficeclass(min_value,max_value) values(0,50);
insert into boxofficeclass(min_value,max_value) values(51,100);
insert into boxofficeclass(min_value,max_value) values(101,150);
insert into boxofficeclass(min_value,max_value) values(151,200);
insert into boxofficeclass(min_value,max_value) values(201,250);
insert into boxofficeclass(min_value,max_value) values(251,300);
insert into boxofficeclass(min_value,max_value) values(301,350);
insert into boxofficeclass(min_value,max_value) values(351,400);
insert into boxofficeclass(min_value,max_value) values(401,450);
insert into boxofficeclass(min_value,max_value) values(451,500);


insert into movie_vgame(name, ReleaseDate, boxoffice, boxofficeclass, ratings, budget, imgfile) values('PK','2014-12-23',500,10, 8.4,300,'/naivebayes/imgs/pk.JPG');
insert into movie_vgame(name, ReleaseDate, boxoffice, boxofficeclass, ratings, budget, imgfile) values('Dhoom 3','2013-12-23',483,10, 3.4,356,'/naivebayes/imgs/d3.JPG');
insert into movie_vgame(name, ReleaseDate, boxoffice, boxofficeclass, ratings, budget, imgfile) values('Chennai Express','2013-10-23',301,6,1.5,302,'/naivebayes/imgs/ce.JPG');
insert into movie_vgame(name, ReleaseDate, boxoffice, boxofficeclass, ratings, budget, imgfile) values('3 Idiots','2009-12-23',492,10,9.9,150,'/naivebayes/imgs/3i.JPG');
insert into movie_vgame(name, ReleaseDate, boxoffice, boxofficeclass, ratings, budget, imgfile) values('Happy New Year','2014-12-23',332,6,0.3,102,'/naivebayes/imgs/hny.JPG');
insert into movie_vgame(name, ReleaseDate, boxoffice, boxofficeclass, ratings, budget, imgfile) values('Maine Pyaar Kyun Kiya','2005-02-23',201,5,0.3,152,'/naivebayes/imgs/hny.JPG');

insert into movie_vgame(name, ReleaseDate, boxoffice, boxofficeclass, ratings, budget, imgfile) values('Talaash','2012-02-03',483,10,0.3,65,'/naivebayes/imgs/hny.JPG');
insert into movie_vgame(name, ReleaseDate, boxoffice, boxofficeclass, ratings, budget, imgfile) values('Ghajini','2008-05-24',433,9,0.3,600,'/naivebayes/imgs/hny.JPG');
insert into movie_vgame(name, ReleaseDate, boxoffice, boxofficeclass, ratings, budget, imgfile) values('Tare Zameen Par','2007-03-21',495,10,0.3,90,'/naivebayes/imgs/hny.JPG');
insert into movie_vgame(name, ReleaseDate, boxoffice, boxofficeclass, ratings, budget, imgfile) values('Fanaa','2006-11-23',500,10,0.3,350,'/naivebayes/imgs/hny.JPG');
insert into movie_vgame(name, ReleaseDate, boxoffice, boxofficeclass, ratings, budget, imgfile) values('Rang De Basanti','2006-01-23',500,10,0.3,120,'/naivebayes/imgs/hny.JPG');


insert into movie_vgame(name, ReleaseDate, boxoffice, boxofficeclass, ratings, budget, imgfile) values('Mangal Pandey','2005-10-05',500,10, 8.4,200,'/naivebayes/imgs/pk.JPG');
insert into movie_vgame(name, ReleaseDate, boxoffice, boxofficeclass, ratings, budget, imgfile) values('Dil Chaahta Hai','2001-02-06',500,10, 3.4,300,'/naivebayes/imgs/d3.JPG');
insert into movie_vgame(name, ReleaseDate, boxoffice, boxofficeclass, ratings, budget, imgfile) values('Lagaan','2001-10-13',422,9,1.5,166,'/naivebayes/imgs/ce.JPG');
insert into movie_vgame(name, ReleaseDate, boxoffice, boxofficeclass, ratings, budget, imgfile) values('Mela','2000-09-03',489,10,9.9,150,'/naivebayes/imgs/3i.JPG');
insert into movie_vgame(name, ReleaseDate, boxoffice, boxofficeclass, ratings, budget, imgfile) values('Mann','1999-11-25',456,10,0.3,256,'/naivebayes/imgs/hny.JPG');
insert into movie_vgame(name, ReleaseDate, boxoffice, boxofficeclass, ratings, budget, imgfile) values('Sarfarosh','1999-10-17',442,9,0.3,635,'/naivebayes/imgs/hny.JPG');
insert into movie_vgame(name, ReleaseDate, boxoffice, boxofficeclass, ratings, budget, imgfile) values('Ghulam','1998-03-13',500,10,0.3,600,'/naivebayes/imgs/hny.JPG');
insert into movie_vgame(name, ReleaseDate, boxoffice, boxofficeclass, ratings, budget, imgfile) values('Ishq','1997-08-30',500,10,0.3,289,'/naivebayes/imgs/hny.JPG');
insert into movie_vgame(name, ReleaseDate, boxoffice, boxofficeclass, ratings, budget, imgfile) values('Raja Hindustani','1996-07-11',500,10,0.3,298,'/naivebayes/imgs/hny.JPG');
insert into movie_vgame(name, ReleaseDate, boxoffice, boxofficeclass, ratings, budget, imgfile) values('Akele Hum Akele Tum','1995-07-01',500,10,0.3,255,'/naivebayes/imgs/hny.JPG');


insert into movie_vgame(name, ReleaseDate, boxoffice, boxofficeclass, ratings, budget, imgfile) values('Bhoothnath Returns','2014-02-23',332,6, 8.4,200,'/naivebayes/imgs/pk.JPG');
insert into movie_vgame(name, ReleaseDate, boxoffice, boxofficeclass, ratings, budget, imgfile) values('Jab Tak Hai Jaan','2012-09-23',301,6, 3.4,300,'/naivebayes/imgs/d3.JPG');
insert into movie_vgame(name, ReleaseDate, boxoffice, boxofficeclass, ratings, budget, imgfile) values('Koochie Koochie Hota Hai','2011-03-23',323,6,1.5,400,'/naivebayes/imgs/ce.JPG');
insert into movie_vgame(name, ReleaseDate, boxoffice, boxofficeclass, ratings, budget, imgfile) values('Don 2','2011-12-25',333,6,9.9,150,'/naivebayes/imgs/3i.JPG');
insert into movie_vgame(name, ReleaseDate, boxoffice, boxofficeclass, ratings, budget, imgfile) values('Ra.One','2011-11-27',322,6,0.3,600,'/naivebayes/imgs/hny.JPG');
insert into movie_vgame(name, ReleaseDate, boxoffice, boxofficeclass, ratings, budget, imgfile) values('My Name Is Khan','2010-10-30',299,5,0.3,600,'/naivebayes/imgs/hny.JPG');
insert into movie_vgame(name, ReleaseDate, boxoffice, boxofficeclass, ratings, budget, imgfile) values('Billu','2009-04-30',295,5,0.3,302,'/naivebayes/imgs/hny.JPG');
insert into movie_vgame(name, ReleaseDate, boxoffice, boxofficeclass, ratings, budget, imgfile) values('Rab Ne Bana Di Jodi','2008-05-03',322,6,0.3,335,'/naivebayes/imgs/hny.JPG');
insert into movie_vgame(name, ReleaseDate, boxoffice, boxofficeclass, ratings, budget, imgfile) values('Bhoothnath','2008-06-04',301,6,0.3,600,'/naivebayes/imgs/hny.JPG');
insert into movie_vgame(name, ReleaseDate, boxoffice, boxofficeclass, ratings, budget, imgfile) values('Om Shanti Om','2007-07-06',301,6,0.3,245,'/naivebayes/imgs/hny.JPG');


insert into movie_vgame(name, ReleaseDate, boxoffice, boxofficeclass, ratings, budget, imgfile) values('Chakde India','2007-02-25',306,6, 8.4,200,'/naivebayes/imgs/pk.JPG');
insert into movie_vgame(name, ReleaseDate, boxoffice, boxofficeclass, ratings, budget, imgfile) values('Don','2006-11-15',315,6, 3.4,300,'/naivebayes/imgs/d3.JPG');
insert into movie_vgame(name, ReleaseDate, boxoffice, boxofficeclass, ratings, budget, imgfile) values('Kabhi Alvida Naa Kehna','2006-10-13',312,6,1.5,400,'/naivebayes/imgs/ce.JPG');
insert into movie_vgame(name, ReleaseDate, boxoffice, boxofficeclass, ratings, budget, imgfile) values('Paheli','2005-11-13',301,6,9.9,150,'/naivebayes/imgs/3i.JPG');
insert into movie_vgame(name, ReleaseDate, boxoffice, boxofficeclass, ratings, budget, imgfile) values('Swades','2004-06-08',301,6,0.3,600,'/naivebayes/imgs/hny.JPG');

insert into movie_vgame(name, ReleaseDate, boxoffice, boxofficeclass, ratings, budget, imgfile) values('Kick','2014-10-23',222,5,0.3,600,'/naivebayes/imgs/hny.JPG');
insert into movie_vgame(name, ReleaseDate, boxoffice, boxofficeclass, ratings, budget, imgfile) values('Jai Ho','2014-10-23',232,5,0.3,600,'/naivebayes/imgs/hny.JPG');
insert into movie_vgame(name, ReleaseDate, boxoffice, boxofficeclass, ratings, budget, imgfile) values('Dabangg 2','2012-11-23',223,5,0.3,600,'/naivebayes/imgs/hny.JPG');
insert into movie_vgame(name, ReleaseDate, boxoffice, boxofficeclass, ratings, budget, imgfile) values('Son Of Sardaar','2012-06-23',221,5,0.3,600,'/naivebayes/imgs/hny.JPG');
insert into movie_vgame(name, ReleaseDate, boxoffice, boxofficeclass, ratings, budget, imgfile) values('Ek Tha Tiger','2012-01-23',225,5,0.3,600,'/naivebayes/imgs/hny.JPG');


insert into movie_vgame(name, ReleaseDate, boxoffice, boxofficeclass, ratings, budget, imgfile) values('Bodyguard','2011-02-23',242,5, 8.4,200,'/naivebayes/imgs/pk.JPG');
insert into movie_vgame(name, ReleaseDate, boxoffice, boxofficeclass, ratings, budget, imgfile) values('Ready','2011-02-01',244,5, 3.4,300,'/naivebayes/imgs/d3.JPG');
insert into movie_vgame(name, ReleaseDate, boxoffice, boxofficeclass, ratings, budget, imgfile) values('Dabangg','2010-10-06',245,5,1.5,400,'/naivebayes/imgs/ce.JPG');
insert into movie_vgame(name, ReleaseDate, boxoffice, boxofficeclass, ratings, budget, imgfile) values('Veer','2010-12-07',241,5,9.9,150,'/naivebayes/imgs/3i.JPG');
insert into movie_vgame(name, ReleaseDate, boxoffice, boxofficeclass, ratings, budget, imgfile) values('London Dreams','2009-10-04',223,5,0.3,600,'/naivebayes/imgs/hny.JPG');
insert into movie_vgame(name, ReleaseDate, boxoffice, boxofficeclass, ratings, budget, imgfile) values('Main Aurr Mrs Khanna','2009-02-02',201,5,0.3,600,'/naivebayes/imgs/hny.JPG');
insert into movie_vgame(name, ReleaseDate, boxoffice, boxofficeclass, ratings, budget, imgfile) values('Wanted','2009-03-03',302,6,0.3,600,'/naivebayes/imgs/hny.JPG');
insert into movie_vgame(name, ReleaseDate, boxoffice, boxofficeclass, ratings, budget, imgfile) values('Yuvvraaj','2008-05-15',356,7,0.3,600,'/naivebayes/imgs/hny.JPG');
insert into movie_vgame(name, ReleaseDate, boxoffice, boxofficeclass, ratings, budget, imgfile) values('Heroes','2008-06-14',236,5,0.3,600,'/naivebayes/imgs/hny.JPG');
insert into movie_vgame(name, ReleaseDate, boxoffice, boxofficeclass, ratings, budget, imgfile) values('Hello','2008-07-16',235,5,0.3,600,'/naivebayes/imgs/hny.JPG');

insert into movie_vgame(name, ReleaseDate, boxoffice, boxofficeclass, ratings, budget, imgfile) values('Jab we met','2008-07-16',235,5,0.3,600,'/naivebayes/imgs/hny.JPG');



insert into personmovie values(1,3,'Actor');
insert into personmovie values(1,23,'Actor');
insert into personmovie values(1,9,'Actor');
insert into personmovie values(1,3,'Director');


insert into personmovie values(2,3,'Actor');
insert into personmovie values(2,16,'Actor');
insert into personmovie values(2,3,'Director');


insert into personmovie values(3,2,'Actor');
insert into personmovie values(3,17,'Actor');
insert into personmovie values(3,2,'Director');


insert into personmovie values(4,3,'Actor');
insert into personmovie values(4,20,'Actor');
insert into personmovie values(4,3,'Director');


insert into personmovie values(5,2,'Actor');
insert into personmovie values(5,17,'Actor');
insert into personmovie values(5,2,'Director');

insert into personmovie values(6,1,'Actor');
insert into personmovie values(6,24,'Actor');

insert into personmovie values(7,3,'Actor');
insert into personmovie values(7,25,'Actor');


insert into personmovie values(8,3,'Actor');
insert into personmovie values(8,26,'Actor');


insert into personmovie values(9,3,'Actor');
insert into personmovie values(9,17,'Actor');


insert into personmovie values(10,3,'Actor');
insert into personmovie values(10,28,'Actor');


insert into personmovie values(11,3,'Actor');
insert into personmovie values(11,27,'Actor');


insert into personmovie values(12,3,'Actor');
insert into personmovie values(12,29,'Actor');


insert into personmovie values(13,3,'Actor');
insert into personmovie values(13,30,'Actor');


insert into personmovie values(14,3,'Actor');
insert into personmovie values(14,31,'Actor');


insert into personmovie values(15,3,'Actor');
insert into personmovie values(15,32,'Actor');


insert into personmovie values(16,3,'Actor');
insert into personmovie values(16,33,'Actor');


insert into personmovie values(17,3,'Actor');
insert into personmovie values(17,34,'Actor');


insert into personmovie values(18,3,'Actor');
insert into personmovie values(18,25,'Actor');


insert into personmovie values(19,3,'Actor');
insert into personmovie values(19,28,'Actor');


insert into personmovie values(20,3,'Actor');
insert into personmovie values(20,35,'Actor');


insert into personmovie values(21,3,'Actor');
insert into personmovie values(21,33,'Actor');


insert into personmovie values(22,2,'Actor');
insert into personmovie values(22,36,'Actor');


insert into personmovie values(23,2,'Actor');
insert into personmovie values(23,16,'Actor');


insert into personmovie values(24,2,'Actor');
insert into personmovie values(24,25,'Actor');


insert into personmovie values(25,2,'Actor');
insert into personmovie values(25,19,'Actor');


insert into personmovie values(26,2,'Actor');
insert into personmovie values(26,20,'Actor');


insert into personmovie values(27,2,'Actor');
insert into personmovie values(27,28,'Actor');


insert into personmovie values(28,2,'Actor');
insert into personmovie values(28,37,'Actor');


insert into personmovie values(29,2,'Actor');
insert into personmovie values(29,23,'Actor');


insert into personmovie values(30,2,'Actor');
insert into personmovie values(30,36,'Actor');


insert into personmovie values(31,2,'Actor');
insert into personmovie values(31,17,'Actor');


insert into personmovie values(32,2,'Actor');


insert into personmovie values(33,2,'Actor');
insert into personmovie values(33,20,'Actor');


insert into personmovie values(34,2,'Actor');
insert into personmovie values(34,25,'Actor');
insert into personmovie values(34,30,'Actor');


insert into personmovie values(35,2,'Actor');
insert into personmovie values(35,25,'Actor');


insert into personmovie values(36,2,'Actor');



insert into personmovie values(37,1,'Actor');
insert into personmovie values(37,16,'Actor');


insert into personmovie values(38,1,'Actor');
insert into personmovie values(38,38,'Actor');


insert into personmovie values(39,1,'Actor');
insert into personmovie values(39,39,'Actor');


insert into personmovie values(40,1,'Actor');
insert into personmovie values(40,39,'Actor');


insert into personmovie values(41,1,'Actor');
insert into personmovie values(41,16,'Actor');


insert into personmovie values(42,1,'Actor');
insert into personmovie values(42,20,'Actor');


insert into personmovie values(43,1,'Actor');
insert into personmovie values(43,26,'Actor');


insert into personmovie values(44,1,'Actor');
insert into personmovie values(44,39,'Actor');


insert into personmovie values(45,1,'Actor');
insert into personmovie values(45,40,'Actor');


insert into personmovie values(46,1,'Actor');
insert into personmovie values(46,26,'Actor');


insert into personmovie values(47,1,'Actor');


insert into personmovie values(48,1,'Actor');
insert into personmovie values(48,41,'Actor');


insert into personmovie values(49,1,'Actor');
insert into personmovie values(49,17,'Actor');


insert into personmovie values(50,1,'Actor');
insert into personmovie values(50,30,'Actor');


insert into personmovie values(51,1,'Actor');
insert into personmovie values(51,16,'Actor');



insert into personmovie values(52,1,'Actor');
insert into personmovie values(52,16,'Actor');

insert into genremovie values(1,1);
insert into genremovie values(1,2);
insert into genremovie values(2,1);
insert into genremovie values(2,2);
insert into genremovie values(2,9);
insert into genremovie values(3,1);
insert into genremovie values(3,2);
insert into genremovie values(4,1);
insert into genremovie values(4,2);
insert into genremovie values(4,6);
insert into genremovie values(5,1);
insert into genremovie values(5,2);










