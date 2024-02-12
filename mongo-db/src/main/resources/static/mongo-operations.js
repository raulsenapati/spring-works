show dbs
use forum
db.createCollection("posts")
show collections

//create
const doc = {...};
db.posts.insertOne(doc);
db.posts.insertMany([{}, {}]);
db.getCollection('posts').insertOne({postId: NumberInt(3333)})

//read
db.getCollection("posts").find({})
db.getCollection("posts").findOne({postId: 3015})
db.getCollection("posts").findOne({comments: 10})
db.getCollection("posts").find({comments: 0})
db.getCollection("posts").find({"author.name": 'Emily Watson'})
db.getCollection("posts").find({tags: 'programming'})
db.getCollection("posts").find({comments: {$gt: 0}})
db.getCollection("posts").find({
    $and: [
        {comments: {$gt: 0}},
        {comments: {$lt: 5}}
    ]
})
db.getCollection("posts").find({
    $or: [
        {shared: true},
        {tags: 'programming'}
    ]
})
//like
db.getCollection("student").find({"mail" : /gmail/})
db.getCollection("posts").find({
    tags: {$in: ['programming', 'coding']}
})
db.getCollection("posts").find({}).limit(2)
db.getCollection("posts").find({}).skip(2)
//default sort is by ObjectId
db.getCollection("posts").find({}).sort({comments: -1})//descending
db.getCollection("posts").find({}).skip(2).sort({shared: 1})
db.getCollection("posts").find({tags: []})
//not exists
db.getCollection("posts").find({title: {$exists: false}})

//update
db.posts.updateOne(
    {postId: 1151}, //query
    {$set: {title: "What is the average salary of the senior frontend developer?"}} //set values
);
db.posts.updateMany(
    {tags: []},
    {$unset: {tags: 1}}
);
db.posts.updateOne(
    {postId: 8451},
    {$inc: {comments: 1}}
);

//delete
db.getCollection("posts").deleteOne({postId: NumberInt(1111)})
db.getCollection("posts").deleteMany({postId: NumberInt(1111)})
db.getCollection("posts").deleteMany({title: {$exists: false}})

//aggregate
db.posts.aggregate([
    {$group: {_id: "$author.nickname"}}
])
db.posts.aggregate([
    {$group: {_id: "$comments"}}
])

//index
db.posts.getIndexes()
db.collection.createIndex(
	{
		"department.id": 1,
		"name": 1
	}
);
db.collection.createIndex(
	{
		"name": 1
	},
	{
		unique: true,
		sparse: true,
		expireAfterSeconds: 3600
	}
);