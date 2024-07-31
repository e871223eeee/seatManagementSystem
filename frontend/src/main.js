import { createApp } from 'vue';
import App from './App.vue';
import router from './router';
import axios from 'axios';

const app = createApp(App);

// 设置 Axios 默认 base URL，指向后端端口
axios.defaults.baseURL = 'http://localhost:8081'; // 确保这里的端口号是你的后端服务器端口号

app.use(router);
app.mount('#app');
