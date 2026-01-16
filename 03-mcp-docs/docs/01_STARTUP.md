# VS Code 與 Cline 安裝測試指南

## 第一步：安裝 VS Code
1. 前往 [VS Code 官網](https://code.visualstudio.com/)。
2. 下載適用於您作業系統（Windows, macOS 或 Linux）的安裝程式。
3. 執行安裝程式並依照指示完成安裝。

## 第二步：安裝 Cline 擴充功能
1. 開啟 VS Code。
2. 點擊左側活動列中的 **Extensions** 圖示（或按下 `Ctrl+Shift+X`）。
3. 在搜尋框輸入 **"Cline"**。
4. 點擊 **Install** 進行安裝。

## 第三步：產生 API Token
1. 根據您想使用的 AI 服務（例如 Anthropic, OpenAI 或 Google Gemini），前往其開發者平台。
2. 登入您的帳戶並導航至 **API Keys** 設定頁面。
3. 點擊 **"Create new secret key"** 並複製產生的 Token（請妥善保管，不要外洩）。

## 第四步：測試
1. 在 VS Code 中開啟 Cline 面板。
2. 進入設定（Settings），將剛才產生的 **API Token** 貼入對應的欄位。
3. 選擇模型後，嘗試在對話框輸入「你好」或「這是一個測試」，確認是否能收到回覆。