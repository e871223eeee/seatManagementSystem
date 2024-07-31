<template>
    <div>
      <h1>座位圖</h1>
      <div v-for="floor in floors" :key="floor.floorNo">
        <h2>樓層 {{ floor.floorNo }}</h2>
        <div v-for="seat in floor.seats" :key="seat.floorSeatSeq" class="seat">
          <div :class="{'occupied': seat.occupied, 'available': !seat.occupied}">
            座位 {{ seat.seatNo }} - {{ seat.occupied ? seat.empId : '空位' }}
            <button @click="selectSeat(seat.floorSeatSeq)" v-if="!seat.occupied">選擇</button>
          </div>
        </div>
      </div>
      <div>
        <label for="employee">選擇員工:</label>
        <select v-model="selectedEmployee">
          <option v-for="employee in employees" :key="employee.empId" :value="employee.empId">{{ employee.name }}</option>
        </select>
        <button @click="submitSeatChange">提交</button>
      </div>
    </div>
  </template>
  
  <script>
  import axios from 'axios';
  
  export default {
    name: 'SeatingChart',
    data() {
      return {
        floors: [], // 樓層數據
        employees: [], // 員工數據
        selectedEmployee: null, // 被選擇的員工ID
        selectedSeat: null, // 被選擇的座位序號
      };
    },
    mounted() {
      this.fetchSeatingChartState();
      this.fetchAllEmployees();
    },
    methods: {
      // 獲取座位狀態數據
      fetchSeatingChartState() {
        axios.get('/api/seatingChart/getSeatingChartState')
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
        axios.get('/api/seatingChart/getAllEmployee')
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
      selectSeat(floorSeatSeq) {
        this.selectedSeat = floorSeatSeq;
      },
      // 提交座位變更
      submitSeatChange() {
        if (!this.selectedEmployee || !this.selectedSeat) {
          alert("請選擇員工和座位。");
          return;
        }
        axios.put('/api/seatingChart/updateEmployeeSeat', {
          empId: this.selectedEmployee,
          floorSeatSeq: this.selectedSeat
        })
          .then(response => {
            if (response.data.code === 0) {
              alert(response.data.message);
              this.fetchSeatingChartState(); // 重新獲取座位資訊
              this.selectedEmployee = null;
              this.selectedSeat = null;
            } else {
              alert(response.data.message);
            }
          })
          .catch(error => {
            console.error("更新座位時發生錯誤:", error);
          });
      }
    }
  }
  </script>
  
  <style>
  .seat {
    margin: 5px;
    padding: 10px;
    border: 1px solid #ccc;
    display: inline-block;
  }
  .occupied {
    background-color: red;
  }
  .available {
    background-color: green;
  }
  </style>
  