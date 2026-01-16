# 讓 AI 產 SPEC

用簡單的業務邏輯說明，讓 AI 幫你產出詳細的技術規格 (SPEC) 文件。

`你是一名資深的軟體系統分析師 (SA)，擅長將模糊的業務需求轉化為極度精確、結構化的開發技術規格。`

## Context & Goal
說明當前的 Context 範圍，以及參與其中的 Entity, Role, Action 等等，並說明最終目標是什麼。

## Requirements
列出所有的功能需求 (Functional Requirements) 與非功能需求 (Non-Functional Requirements)，並針對每個需求進行說明。

### UseCases
1. Use Case 1: [Use Case Title]
   - Description: [Detailed description of the use case]
   - Actors: [List of actors involved]
   - Pre-conditions: [Conditions that must be met before the use case can start]
   - Post-conditions: [Conditions that will be true after the use case is completed]
   - Main Flow: [Step-by-step flow of the use case]
   - Alternate Flows: [Any alternate flows or exceptions]
2. Use Case 2: [Use Case Title]
    - ...

---
# 範例

你是一名資深的軟體系統分析師 (SA)，擅長將模糊的業務需求轉化為極度精確、結構化的開發技術規格。

## Context & Goal
這個 Context 專門負責 客戶基本資料維護，包括查詢、儲存、刪除、還原等操作。
### Entities
1. 客戶 Customer
2. 當前用戶 CurrentUser
   1. 系統角色

### Roles
1. 系統管理員 ('ADM'): 僅有查詢的權限，可查詢全行的客戶
2. 分行經辦 ('TMU1001'): 可執行查詢、儲存、刪除，僅能查詢自己分行的客戶
3. 分行主管 ('TMU1002'): 可執行查詢、儲存、刪除、還原，僅能查詢自己分行的客戶
4. TMU經辦 ('TMU2001'): 可執行查詢、儲存、刪除，可查詢全行的客戶
5. TMU主管 ('TMU2002'): 可執行查詢、儲存、刪除、還原，可查詢全行的客戶

## Use Cases
1. 查詢客戶清單
   - Description: 依 分行別、統編/身分證字號、名稱、狀態，等查詢條件、分頁狀態(單頁幾筆、當前第幾頁)，以及當前登入人員的角色，查出客戶列表，列表需分頁
     - 欄位
       1. 分行別
       2. 統編 / 身分證字號
       3. 名稱
       4. 聯絡人
       5. 聯絡電話
       6. 狀態
   - 狀態為已刪除 (DELETED) 的客戶不查出來
   - Exception
     - 無法辨識當前的登入人員
     - 當前的使用者屬於在允許的角色之外
     - 沒有定義分頁狀態
2. 查詢單筆客戶
   - Description: 依 ID，以及當前登入人員的角色，查出客戶
   - Exception
     - 無法辨識當前的登入人員
     - 當前的使用者屬於在允許的角色之外
     - Id 沒有定義
     - Id 查不到
     - 客戶不能被當前的使用者查詢
     - 客戶狀態為已刪除 (DELETED)
3. 儲存客戶
   - Description: 把前端傳進來的客戶資料，存入資料庫。如果有傳 Id，就用更新；沒有Id就是新增。
   - Exception:
     - 無法辨識當前的登入人員
     - 當前的使用者屬於在允許的角色之外
     - 有 Id 但是找不到客戶
     - 有 Id 但是狀態為 已刪除(DELETED)
     - 當前使用者如果是分行經辦或分行主管，儲存的客戶的分行別是其他的分行
     - 統編/身分證字號為空值
     - 分行別為空值
     - 名稱為空值
4. 刪除客戶
   - Description: 依客戶的 Id，把客戶的狀態壓成 "已刪除" (DELETED)
   - Exception:
     - 無法辨識當前的登入人員
     - 當前的使用者屬於在允許的角色之外
     - 有 Id 但是找不到客戶
     - 有 Id 但是狀態為 已刪除(DELETED)
4. 復原客戶
   - Description: 依客戶的 Id，把客戶的狀態壓成 "啟用中" (ENABLED)
   - Exception:
     - 無法辨識當前的登入人員
     - 當前的使用者屬於在允許的角色之外
     - 有 Id 但是找不到客戶
     - 有 Id 但是狀態為 已刪除(ENABLED)
