# 開發規範

## 主要原則

1. 用 Java 11 進行開發
2. 將程式碼放在 /src 內
	- {package 自行定義}
3. 請依據 clean architecture 的精神
	- Service 和 Entity 為業務邏輯核心
	- Service 對外取得的資源用 Port 來介接
4. 資料夾檔案位置
	- Entity: /domainModel
	- Service: 
		- 介面 UseCase: /useCase
		- 實作 Service: /useCase/Impl
	- Port: 
		- 介面: Port: /port
		- 實作: Adapter: /port/Impl
		- 範例:
			- Port 介面：UserRepositoryPort
			- Adapter 實作：UserRepositoryAdapter 或 JdbcUserRepositoryAdapter
5. 檔案用 UTF-8 編碼


## 命名規範

- class, method, property, const 都需要中文註解


### 1. 類別 (Class)

* **命名法：** **UpperCamelCase**（大駝峰式命名法）。每個單字的首字母都大寫。
* **詞性：** 應使用 **名詞**。
* **原則：** 命名應簡潔且具有描述性，避免使用縮寫（除非是常見的縮寫如 HTTP、URL）。
	- 如果有縮寫，第一個字大寫，後面都小寫
	- 範例:
		- HttpSender
		- JsonParser
* **範例：**
  * `UserAccount`
  * `OrderProcessor`
  * `DataParser`

### 2. 方法 (Method)

* **命名法：** **lowerCamelCase**（小駝峰式命名法）。第一個單字首字母小寫，其後單字首字母大寫。
* **詞性：** 應以 **動詞** 開頭。
* **原則：** 通常反映該方法執行的動作。
* **常用慣例：**
* 取得資料：`getAmount()`
* 設定資料：`setStatus()`
* 判斷布林值：`isEmpty()`、`hasToken()`
* **範例：**
  * `calculateTotal()`
  * `sendEmail()`
  * `fetchUserRecord()`

### 3. 屬性 / 變數 (Property / Variable)

* **命名法：** **lowerCamelCase**（小駝峰式命名法）。
* **詞性：** 應使用 **名詞**。
* **原則：** 應清楚表達該變數代表的意義。避免使用單一字母（如 `a`, `b`），除非是在迴圈中的索引（如 `i`, `j`）。
* **範例：**
  * `userName`
  * `totalPrice`
  * `isPublished`

### 4. 常數 (Constant)

* **命名法：** **SNAKE_CASE**（全大寫蛇形命名法）。所有字母大寫，單字間以底線 `_` 分隔。
* **範例：**
* `MAX_RETRY_COUNT`
* `DEFAULT_TIMEOUT`

## Exception
- 做一個基礎底層 {Exception}，放在 common/exceptions 資料夾裡
   - 各種子類別，就繼承基礎類別
- 

## 測試案例
1. 用 JUnit 5 寫 test, 用 mockito 做 mock
2. 每個 Service 都該有單元測試來驗證正確性
3. 如果 Entity 有行為，就該用單元測試來驗證正確性
4. 單元測試須包含
	1. 空值或 null 
	2. 輸入參數邊界
	3. 快樂路徑
	4. 業務邏輯驗證
5. 單元測試需要加入 DisplayName，用中文描述測試情境或條件
6. 檔案路徑需對應 .java 的資料夾結構
