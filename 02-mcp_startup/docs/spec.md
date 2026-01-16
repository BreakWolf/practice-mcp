# 記帳 API 規格文件

## 1. 簡介

此文件定義了記帳 API 的規格，旨在提供一個標準化的介面，用於管理個人或小組的收支記錄。API 將支援帳戶、交易紀錄的建立、查詢、更新與刪除。

## 2. 認證 (Authentication)

API 將採用基於 Token 的認證機制。使用者需要先登入以取得 Token，之後在每次請求時將 Token 包含在 `Authorization` 標頭中。

## 3. 錯誤處理 (Error Handling)

API 將使用標準的 HTTP 狀態碼來表示請求的結果。當發生錯誤時，回應會包含一個 JSON 物件，其中包含錯誤訊息和錯誤代碼。

```json
{
  "message": "錯誤訊息",
  "code": "錯誤代碼"
}
```

## 4. API 端點 (API Endpoints)

### 4.1. 帳戶 (Accounts)

#### 4.1.1. 建立帳戶

- **URL:** `/accounts`
- **Method:** `POST`
- **描述:** 建立一個新的帳戶。
- **請求範例:**
  ```json
  {
    "name": "現金帳戶",
    "initial_balance": 10000
  }
  ```
- **回應範例:**
  ```json
  {
    "id": "acc_001",
    "name": "現金帳戶",
    "balance": 10000,
    "created_at": "2023-01-01T10:00:00Z"
  }
  ```

#### 4.1.2. 取得所有帳戶

- **URL:** `/accounts`
- **Method:** `GET`
- **描述:** 取得所有帳戶的列表。
- **回應範例:**
  ```json
  [
    {
      "id": "acc_001",
      "name": "現金帳戶",
      "balance": 10000,
      "created_at": "2023-01-01T10:00:00Z"
    },
    {
      "id": "acc_002",
      "name": "銀行帳戶",
      "balance": 50000,
      "created_at": "2023-01-01T10:05:00Z"
    }
  ]
  ```

#### 4.1.3. 取得單一帳戶

- **URL:** `/accounts/{account_id}`
- **Method:** `GET`
- **描述:** 取得指定 `account_id` 的帳戶資訊。
- **回應範例:**
  ```json
  {
    "id": "acc_001",
    "name": "現金帳戶",
    "balance": 10000,
    "created_at": "2023-01-01T10:00:00Z"
  }
  ```

#### 4.1.4. 更新帳戶

- **URL:** `/accounts/{account_id}`
- **Method:** `PUT`
- **描述:** 更新指定 `account_id` 的帳戶資訊。目前只允許更新帳戶名稱。
- **請求範例:**
  ```json
  {
    "name": "我的現金帳戶"
  }
  ```
- **回應範例:**
  ```json
  {
    "id": "acc_001",
    "name": "我的現金帳戶",
    "balance": 10000,
    "created_at": "2023-01-01T10:00:00Z"
  }
  ```

#### 4.1.5. 刪除帳戶

- **URL:** `/accounts/{account_id}`
- **Method:** `DELETE`
- **描述:** 刪除指定 `account_id` 的帳戶。
- **回應範例:** `204 No Content`

### 4.2. 交易 (Transactions)

#### 4.2.1. 建立交易

- **URL:** `/transactions`
- **Method:** `POST`
- **描述:** 建立一筆新的交易紀錄。
- **請求範例:**
  ```json
  {
    "account_id": "acc_001",
    "type": "expense",
    "category": "餐飲",
    "amount": 150,
    "description": "午餐",
    "date": "2023-01-01"
  }
  ```
- **回應範例:**
  ```json
  {
    "id": "txn_001",
    "account_id": "acc_001",
    "type": "expense",
    "category": "餐飲",
    "amount": 150,
    "description": "午餐",
    "date": "2023-01-01",
    "created_at": "2023-01-01T12:30:00Z"
  }
  ```

#### 4.2.2. 取得所有交易

- **URL:** `/transactions`
- **Method:** `GET`
- **描述:** 取得所有交易紀錄的列表。可選用 `account_id` 和 `category` 進行篩選。
- **查詢參數:**
  - `account_id` (可選): 根據帳戶 ID 篩選交易。
  - `category` (可選): 根據類別篩選交易。
- **回應範例:**
  ```json
  [
    {
      "id": "txn_001",
      "account_id": "acc_001",
      "type": "expense",
      "category": "餐飲",
      "amount": 150,
      "description": "午餐",
      "date": "2023-01-01",
      "created_at": "2023-01-01T12:30:00Z"
    },
    {
      "id": "txn_002",
      "account_id": "acc_001",
      "type": "income",
      "category": "薪資",
      "amount": 30000,
      "description": "月薪",
      "date": "2023-01-05",
      "created_at": "2023-01-05T09:00:00Z"
    }
  ]
  ```

#### 4.2.3. 取得單一交易

- **URL:** `/transactions/{transaction_id}`
- **Method:** `GET`
- **描述:** 取得指定 `transaction_id` 的交易紀錄。
- **回應範例:**
  ```json
  {
    "id": "txn_001",
    "account_id": "acc_001",
    "type": "expense",
    "category": "餐飲",
    "amount": 150,
    "description": "午餐",
    "date": "2023-01-01",
    "created_at": "2023-01-01T12:30:00Z"
  }
  ```

#### 4.2.4. 更新交易

- **URL:** `/transactions/{transaction_id}`
- **Method:** `PUT`
- **描述:** 更新指定 `transaction_id` 的交易紀錄。只允許更新 `type`, `category`, `amount`, `description`, `date` 欄位。
- **請求範例:**
  ```json
  {
    "type": "expense",
    "category": "交通",
    "amount": 50,
    "description": "公車費",
    "date": "2023-01-02"
  }
  ```
- **回應範例:**
  ```json
  {
    "id": "txn_001",
    "account_id": "acc_001",
    "type": "expense",
    "category": "交通",
    "amount": 50,
    "description": "公車費",
    "date": "2023-01-02",
    "created_at": "2023-01-01T12:30:00Z"
  }
  ```

#### 4.2.5. 刪除交易

- **URL:** `/transactions/{transaction_id}`
- **Method:** `DELETE`
- **描述:** 刪除指定 `transaction_id` 的交易紀錄。
- **回應範例:** `204 No Content`
