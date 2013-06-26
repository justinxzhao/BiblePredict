BiblePredict
============

Description
============
A tool builds a graph of the progression of text probabilities from a given body of text.
Specific use case: The Bible, King James version

Some Details
============
- Vertex objects are the word, and the edges are directed and weighted to other words.
- Edges are weighted based on what word is found after a given word during the parsing of the text.
- Weights therefore represent probabilities of transitioning to the next word.
- Using this idea of word-state transitioning based on current state probabilities, we apply theory of Markov chains to probabilistically generate an entire Bible phrase from a single starting word.

Example List of Probabilities for a Given Word
==============================================
'Jacob'
-	was 0.041884816753926704
-	sod 0.005235602094240838
-	said, 0.02617801047120419
-	gave 0.010471204188481676
-	her 0.015706806282722512
-	said 0.05759162303664921
-	went 0.03664921465968586
-	because 0.005235602094240838
-	take 0.005235602094240838
-	obeyed 0.005235602094240838
-	awaked 0.005235602094240838
-	rose 0.015706806282722512
-	vowed 0.005235602094240838
-	saw 0.015706806282722512
-	kissed 0.005235602094240838
-	told 0.005235602094240838
-	his 0.02617801047120419
-	loved 0.005235602094240838
-	served 0.005235602094240838
-	did 0.010471204188481676
-	no 0.005235602094240838
-	a 0.020942408376963352
-	to 0.02617801047120419
-	came 0.031413612565445025
-	the 0.010471204188481676
-	fed 0.005235602094240838
-	took 0.010471204188481676
-	laid 0.005235602094240838
-	hath 0.005235602094240838
-	beheld 0.005235602094240838
-	sent 0.015706806282722512
-	stole 0.005235602094240838
-	either 0.010471204188481676
-	had 0.010471204188481676
-	answered 0.015706806282722512
-	knew 0.005235602094240838
-	called 0.020942408376963352
-	sware 0.005235602094240838
-	offered 0.005235602094240838
-	saith 0.005235602094240838
-	is 0.03664921465968586
-	asked 0.005235602094240838
-	lifted 0.005235602094240838
-	journeyed 0.005235602094240838
-	heard 0.010471204188481676
-	held 0.005235602094240838
-	all 0.005235602094240838
-	hid 0.005235602094240838
-	again, 0.005235602094240838
-	set 0.010471204188481676
-	were 0.010471204188481676
-	buried 0.005235602094240838
-	dwelt 0.005235602094240838
-	rent 0.005235602094240838
-	their 0.031413612565445025
-	and 0.04712041884816754
-	in 0.005235602094240838
-	into 0.005235602094240838
-	blessed 0.010471204188481676
-	lived 0.005235602094240838
-	shall 0.06806282722513089
-	thy 0.010471204188481676
-	bought 0.005235602094240838
-	for 0.02617801047120419
-	defend 0.005235602094240838
-	whom 0.010471204188481676
-	unto 0.010471204188481676
-	regard 0.005235602094240838
-	sojourned 0.005235602094240838
-	from 0.005235602094240838
-	be 0.005235602094240838
-	my 0.020942408376963352
-	again 0.005235602094240838
-	like 0.005235602094240838
-	according 0.005235602094240838
-	fled 0.005235602094240838
-	arise? 0.010471204188481676
-	shame 0.005235602094240838
-	are 0.005235602094240838
-	begat 0.015706806282722512
-	have 0.005235602094240838

'twelve'
-	years: 0.015748031496062992
-	princes 0.015748031496062992
-	brethren, 0.015748031496062992
-	tribes 0.05511811023622047
-	wells 0.007874015748031496
-	pillars, 0.007874015748031496
-	tribes. 0.015748031496062992
-	cakes 0.007874015748031496
-	men: 0.007874015748031496
-	oxen; 0.007874015748031496
-	chargers 0.007874015748031496
-	silver 0.007874015748031496
-	spoons 0.007874015748031496
-	bullocks, 0.007874015748031496
-	rods: 0.015748031496062992
-	young 0.007874015748031496
-	thousand 0.07874015748031496
-	fountains 0.007874015748031496
-	men 0.023622047244094488
-	stones, 0.023622047244094488
-	men, 0.007874015748031496
-	stones 0.015748031496062992
-	thousand, 0.007874015748031496
-	cities 0.015748031496062992
-	cities. 0.023622047244094488
-	pieces, 0.007874015748031496
-	of 0.023622047244094488
-	officers 0.007874015748031496
-	cubits 0.023622047244094488
-	oxen, 0.015748031496062992
-	oxen 0.015748031496062992
-	lions 0.015748031496062992
-	pieces: 0.007874015748031496
-	yoke 0.007874015748031496
-	years. 0.015748031496062992
-	years 0.031496062992125984
-	hundred 0.007874015748031496
-	he 0.015748031496062992
-	bullocks 0.007874015748031496
-	years, 0.031496062992125984
-	months, 0.007874015748031496
-	brasen 0.007874015748031496
-	broad, 0.007874015748031496
-	months 0.007874015748031496
-	disciples, 0.015748031496062992
-	apostles 0.023622047244094488
-	Jesus 0.007874015748031496
-	baskets 0.023622047244094488
-	thrones, 0.007874015748031496
-	disciples 0.015748031496062992
-	legions 0.007874015748031496
-	asked 0.007874015748031496
-	were 0.007874015748031496
-	baskets. 0.007874015748031496
-	hours 0.007874015748031496
-	called 0.007874015748031496
-	patriarchs. 0.007874015748031496
-	days 0.007874015748031496
-	tribes, 0.007874015748031496
-	thousand. 0.09448818897637795
-	stars: 0.007874015748031496
-	gates, 0.007874015748031496
-	angels, 0.007874015748031496
-	foundations, 0.007874015748031496
-	gates 0.007874015748031496
-	pearls: 0.007874015748031496
-	manner 0.007874015748031496

Trends with Generate Bible Phrases
==================================
- Sentences that make sense
- Minimal/No grammar errors

Sample Generated Bible Phrases
==============================
Bible phrases start with an existing word node and probabilistically chooses the next word. Phrase finishes if current visited node ends in a '.', '?', or ';'

(Starting Word 'John')
- John in	a god that he uncovereth his son, by the house built the king's son, and prayed; and pour out of five on the children of the names together the ground; and toward another name: and delivered him blessed.
- John to build upon the whole congregation of wood. 
- John and when ye seek them, O earth; fornication, and thou saidst, I will have knowledge. 
- John his father, and consume them out as the LORD hath he gave them above. 
- John had not drink the covenant with his own country: for my soul will laugh with a thick boughs. 
- John his hand; but according to cease, and with me to instruct him? 
- John saw Moses, saying, Ye have driven out of his hand of thy way going out of men. 
- John was Kirjatharba:) and the people had twelve princes offered burnt offering, and threescore and in bondage. 
- John the feast: for because his righteousness' sake; for the land of Azel. 
- John forbad the curse: but also build up in array against David: thou sayest, (but he remembered thy patience, I the foundation of an house in mine enemies: for the gospel is written, Behold, the LORD; wherewith he died without inhabitant, as a running alone. 
- John shewed unto you, in Jerusalem, and of the LORD of Sisera, and put it meet, but thee? 

(Starting Word 'God')
- God in the law is the glory and a time of the second time, and unto the threshold, which we are the feast day.
- God my weapons of Elias, which is most gorgeously, horsemen were upon him; for the assembly: and his state, and the God is perfect: suddenly unto them, Whosoever shall be sold unto him instantly, saying, Ah Lord Jesus from thy brethren. 
- God will hate thee to justify yourselves in the same? 
- God in Asia, which no comforter. 
- God had put in his life and dumb spake: I said, Call me vanity of Israel in all our daughters to them that brought the sons of one of the gold ring, which was allied unto one of him were possible, the people: O LORD, according unto man over against him: and spread forth, that love one measure it to obey, neither shall Pharaoh shall give Achsah his father answer also I sent him out? 
- God is in peace. 
- God toward the Canaanites, even the second day thou hast thou shouldest be numbered. 
- God resisteth the wilderness? 
- God brought him the children of a bed in Egypt to die. 
- God of bonds. 
- God is given them that were present at hand.