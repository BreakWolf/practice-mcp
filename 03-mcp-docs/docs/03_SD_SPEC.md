## 設計規格

這裡列出一些要件。
滿足這些要件，AI比較能夠依照這些要素去產出符合規範的程式碼。

### 樣版

Context 名稱
- UseCase 名稱
- Method 名稱
  - Input Entity (參數)
  - Output Entity (Return)
- 需求說明: 明確說明這支程式「要做什麼」。
  - 使用者故事 (User Stories)：以「身為 [角色]，我想要 [功能]，以便於 [價值]」的格式撰寫。
-  驗證條件
     -  不滿足的條件 Exception 如何展示
     -  系統的資料會產生什麼變化 (ex. 改狀態、寫入資料...等) 

### 資料模型定義
#### Entity名稱 
| 欄位名稱 | 型別 | 說明 | 驗證規則 |
| :--- | :--- | :--- | :--- |
| | | | |

---

## 範例:

**CustomerManagement 基本資料維護**
- SaveCustomerUseCase 儲存客戶
- Save 儲存 
    - Input: Customer 客戶
    - Output: string 儲存結果 (成功/失敗)
- 驗證條件
  - 找不到 CurrentUser，則跳出 TMUException: 非TMU用戶，無此編輯權限
  - 不屬於 TMU 用戶，則跳出 TMUException: 非TMU用戶，無此編輯權限
  - 統編/身分證格式錯誤，則跳出 TMUException: 錯誤的統編格式
  - 名字超過100碼，則跳出 TMUException: 客戶名稱(中文)不可為空或超過100碼
  - 如果輸入的 Customer 沒有包含 Id, 就 Insert 新的客戶；否則就 Update 舊客戶
  - 如果為新增，回傳 "新增成功"
  - 如果為編輯，回傳 "編輯成功"

### 資料模型定義

#### Customer
| 欄位名稱 | 型別 | 說明 | 驗證規則 |
| :--- | :--- | :--- | :--- |
| Id | String | 主鍵 (UUID) | 可為空 (新增時) |
| Name | String | 客戶名稱 | 必填, Max: 100 |
| TaxId | String | 統一編號 | 必填, 格式檢查 |