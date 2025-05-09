<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <title>Danh sách ghế phòng chiếu số 8</title>
  <style>
    body {
      font-family: Arial, sans-serif;
      text-align: center;
      margin: 30px;
    }

    .seat-container {
      display: grid;
      grid-template-columns: repeat(10, 70px);
      grid-gap: 10px;
      justify-content: center;
      margin-bottom: 20px;
    }

    .seat {
      width: 70px;
      height: 70px;
      border: 1px solid #ccc;
      background-color: #f0f0f0;
      cursor: pointer;
      font-size: 12px;
      font-weight: bold;
      line-height: 16px;
      border-radius: 5px;
      display: flex;
      flex-direction: column;
      justify-content: center;
      align-items: center;
    }

    .seat.selected {
      background-color: #007bff;
      color: white;
    }

    .seat.booked {
      background-color: #999;
      color: white;
      cursor: not-allowed;
    }

    button {
      padding: 10px 20px;
      font-size: 16px;
    }

    .message {
      margin-top: 20px;
      font-weight: bold;
    }
  </style>
  <script>
    function toggleSeat(seatDiv) {
      if (seatDiv.classList.contains('booked')) return;
      seatDiv.classList.toggle('selected');
      updateTotal();
    }

    function updateTotal() {
      const selectedSeats = document.querySelectorAll('.seat.selected');
      let total = 0;
      selectedSeats.forEach(seat => {
        total += parseFloat(seat.dataset.price);
      });

      document.getElementById("totalAmount").innerText = "Tổng tiền: " + total.toLocaleString() + " VND";
    }

    function thanhToan() {
      const selectedSeats = document.querySelectorAll('.seat.selected');
      let seats = [];
      selectedSeats.forEach(seat => seats.push(seat.dataset.seatId));
      let msg = document.getElementById("message");
      if (seats.length === 0) {
        msg.innerText = "Vui lòng chọn ít nhất một ghế.";
        msg.style.color = "red";
      } else {
        msg.innerText = "Bạn đã chọn các ghế: " + seats.join(", ");
        msg.style.color = "green";
      }
    }
  </script>
</head>
<body>
<h2>Danh sách ghế phòng chiếu số 8</h2>

<div class="seat-container">
  <c:forEach var="seat" items="${seats}">
    <div class="seat ${seat.status == 'booked' ? 'booked' : ''}"
         data-seat-id="${seat.name}"
         data-price="${seat.price}"
         onclick="toggleSeat(this)">
        ${seat.name}<br/>
        ${seat.price}đ
    </div>
  </c:forEach>
</div>

<p id="totalAmount">Tổng tiền: 0 VND</p>

<button type="button" onclick="thanhToan()">Thanh toán</button>
<p id="message" class="message"></p>
</body>
</html>
