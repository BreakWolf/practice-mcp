// index.js - 測試進入點
const { login } = require('./auth');

const result = login('admin', '1234');
console.log('測試登入結果:', result.message);