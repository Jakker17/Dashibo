db = db.getSiblingDB('dashibo')

db.createUser({
    user: 'user',
    pwd: 'user',
    roles: [{ role: 'readWrite', db: 'dashibo' }],
});

db.createCollection('users');