# 員工座位管理系統

本專案是一個員工座位管理系統，旨在為人資部門提供方便的員工座位安排和管理功能。

## 安裝步驟

### 前端部分

1. 克隆專案

   首先，使用 Git 將專案克隆到本地：
   ```
   git clone https://github.com/e871223eeee/seatManagementSystem.git
   cd seatManagementSystem/frontend
   ```
2. 安裝依賴

   使用 npm 安裝所有必需的依賴：
   ```
   npm install
   ```
3. 使用 axios 與後端交互

   本專案使用 `axios` 來與後端 API 進行交互。確保你已經安裝 `axios`：
   ```
   npm install axios
   ```
4. 運行開發伺服器

   運行以下命令來啟動前端：
   ```
   npm run serve
   ```
   預設情況下，應用程式將在 `http://localhost:8080` 運行。

### 文件結構

以下是專案的基本文件結構：
```
frontend/
│
├── public/
│   ├── index.html
│
├── src/
│   ├── assets/
│   ├── components/
│   ├── router/
│   │   ├── index.js
│   ├── styles/
│   │   ├── SeatingChart.css
│   ├── views/
│   │   ├── HomeView.vue
│   ├── App.vue
│   ├── main.js
│
├── package.json
└── README.md
```
### 主要文件說明

- public/index.html: 主 HTML 文件。
- src/App.vue: 主 Vue 組件。
- src/main.js: Vue 應用程式入口文件。
- src/router/index.js: 路由配置文件。
- src/views/HomeView.vue: 首頁視圖文件。
- src/styles/SeatingChart.css: 座位圖樣式文件。

### 後端部分

0. 克隆專案

   首先，使用 Git 將專案克隆到本地(如果你還沒克隆的話)：
   ```
   git clone https://github.com/yourusername/seatManagementSystem.git
   cd seatManagementSystem
   ```
1. 配置資料庫

   確保你已經安裝並運行了 MySQL。創建一個新的資料，例如 `seatManagementSystem`。

2. 創建資料表

   進入 `DB` 資料夾，執行 `schema.sql` 和 `data.sql` 來創建和初始化資料表：
   ```
   mysql -u root -p seat_management < DB/schema.sql
   mysql -u root -p seat_management < DB/data.sql
   ```
3. 配置資料庫連接

   打開 `src/main/resources/application.yml` 文件，根據你的資料庫配置更新以下參數：
   ```
   spring:
     datasource:
       driver-class-name: com.mysql.cj.jdbc.Driver
       url: jdbc:mysql://localhost:3306/seatManagementSystem
       username: root(你的mysql名稱)
       password: root(你的mysql密碼)
   ```

4. 安裝 Maven 依賴

   在專案根目錄下運行以下命令來安裝所需的 Maven 依賴：
   ```
   mvn clean install
   ```
5. 運行 Spring Boot 應用程式

   使用以下命令來啟動 Spring Boot 應用程式：
   ```
   mvn spring-boot:run
   ```
   應用程式將在 `http://localhost:8081` 運行。

## 使用方法

1. 打開瀏覽器並訪問 `http://localhost:8080` 以查看前端介面。
2. 使用下拉選單選擇員工並調整座位分配。
3. 點擊提交按鈕以保存更改。

## 實際畫面參考

## 初始畫面
<center>
    <img src="https://github.com/user-attachments/assets/b904440d-6525-4bcc-957a-f50bcb42724d" alt="畫面一">
</center>

## 勾選顯示尚未分配座位置員工checkbox後
<center>
    <img src="https://github.com/user-attachments/assets/80d41bde-05f5-439d-b238-4a930e4a7f75" alt="畫面二">
</center>

## 選擇座位以及員工
<center>
    <img src="https://github.com/user-attachments/assets/ad745952-c3e9-4666-9eda-de2c55fcc1b0" alt="畫面三">
</center>

