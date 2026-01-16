// auth.js - 簡單的驗證邏輯
function login(username, password) {
  if (username === 'admin' && password === '1234') {
    return { success: true, message: '登入成功！' };
  } else {
    return { success: false, message: '帳號或密碼錯誤。' };
  }
}

module.exports = { login };