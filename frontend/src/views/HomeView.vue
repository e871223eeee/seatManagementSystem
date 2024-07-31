<template>
  <div>
    <h1>座位圖</h1>
    <div v-for="floor in floors" :key="floor.floorNo" class="floor">
      <div class="seating-row">
        <div v-for="seat in floor.seats" :key="seat.floorSeatSeq" class="seat">
          <button @click="selectSeat(seat)" 
                  class="seatbutton"
                  :class="{'occupied': seat.occupied, 'selected': seat.floorSeatSeq === selectedSeat}">
            {{ formatSeatText(seat) }}
          </button>
        </div>
      </div>
    </div>
    <div>
      <label>
        <input type="checkbox" v-model="showUnassignedEmployees"> 顯示未分配座位的員工
      </label>
      <select v-model="selectedEmployee">
        <option value="0">請選擇員工</option>
        <option v-for="employee in filteredEmployees" :key="employee.empId" :value="employee.empId">
          {{ employee.name }} ({{ employee.empId }})
        </option>
      </select>
      <button class="submitbutton" @click="submitSeatChange">提交</button>
    </div>
    <!-- 狀態說明區塊 -->
    <div class="status-legend">
      <div class="legend-item">
        <div class="legend-color empty"></div>
        <span>空位</span>
      </div>
      <div class="legend-item">
        <div class="legend-color selected"></div>
        <span>選擇中</span>
      </div>
      <div class="legend-item">
        <div class="legend-color occupied"></div>
        <span>有人</span>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios';
// import VueSanitize from 'vue-sanitize';
import '@/styles/SeatingChart.css'; // 引入外部CSS文件

export default {
  name: 'SeatingChart',
  //資料
  data() {
    return {
      floors: [], // 樓層數據
      employees: [], // 員工數據
      selectedEmployee: '0', // 被選擇的員工ID
      selectedSeat: null, // 被選擇的座位序號
      showUnassignedEmployees: false, // 是否顯示未分配座位的員工
    };
  },
  //初始化
  mounted() {
    this.statusUnified()
  },
  
  //計算屬性
  computed: {
    // 根據選擇的顯示選項過濾員工
    filteredEmployees() {
      if (this.showUnassignedEmployees) {
        return this.employees.filter(employee => employee.floorSeatSeq === null);
      } else {
        return this.employees;
      }
    }
  },

  //方法區
  methods: {
    // 獲取座位狀態數據
    fetchSeatingChartState() {
      axios.get('/SeatingChart/getSeatingChartState')
        .then(response => {
          if (response.data.code === 0) {
            this.floors = this.groupByFloor(response.data.data);
          } else {
            alert(response.data.message);
          }
        })
        .catch(error => {
          console.error("獲取座位狀態數據時發生錯誤:", error);
        });
    },
    // 獲取所有員工數據
    fetchAllEmployees() {
      axios.get('/employee/getAllEmployee')
        .then(response => {
          if (response.data.code === 0) {
            this.employees = response.data.data;
          } else {
            alert(response.data.message);
          }
        })
        .catch(error => {
          console.error("獲取所有員工數據時發生錯誤:", error);
        });
    },
    //狀態統一重新整理
    statusUnified(){
      this.fetchSeatingChartState();
      this.fetchAllEmployees();
      this.selectedEmployee = '0';
      this.selectedSeat = null;
    },
    // 按樓層分組座位數據
    groupByFloor(seatingChartState) {
      const floors = {};
      seatingChartState.forEach(seat => {
        if (!floors[seat.floorNo]) {
          floors[seat.floorNo] = [];
        }
        floors[seat.floorNo].push(seat);
      });
      return Object.keys(floors).map(floorNo => ({
        floorNo,
        seats: floors[floorNo]
      }));
    },
    // 選擇座位
    selectSeat(seat) {
      if (seat.occupied) {
        const confirmation = confirm("你選擇了有人的位置，是否將其清除成空位？");
        if (confirmation) {
          axios.put('/employee/updateEmployeeSeat', null, {
            params: {
              empId: seat.empId,
              floorSeatSeq: null
            }
          })
            .then(response => {
              if (response.data.code === 0) {
                alert(response.data.message);
                this.statusUnified()
              } else {
                alert(response.data.message);
              }
            })
            .catch(error => {
              console.error("更新座位時發生錯誤:", error);
            });
        }
      } else {
        this.selectedSeat = seat.floorSeatSeq;
      }
    },
    // 提交座位變更
    submitSeatChange() {
      if ((this.selectedEmployee == '0') || !this.selectedSeat) {
        alert("請選擇員工和座位。");
        return;
      }
      axios.put('/employee/updateEmployeeSeat', null, {
        params: {
          empId: this.selectedEmployee,
          floorSeatSeq: this.selectedSeat
        }
      })
        .then(response => {
          if (response.data.code === 0) {
            alert(response.data.message);
            this.statusUnified()
          } else {
            alert(response.data.message);
          }
        })
        .catch(error => {
          console.error("更新座位時發生錯誤:", error);
        });
    },
    // 格式化座位文本
    formatSeatText(seat) {
      const floorSeatText = ` ${seat.floorNo} \n座位: ${seat.seatNo}`;
      if (seat.occupied) {
        return `${floorSeatText} \n員工編號: ${seat.empId}`;
      } else if (seat.floorSeatSeq === this.selectedSeat) {
        return `${floorSeatText}\n已選擇`;
      } else {
        return floorSeatText;
      }
    }
  }
}
</script>