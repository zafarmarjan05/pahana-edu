<%@ page import="java.util.*, model.book, model.customer" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="header.jsp" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add Bill</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .form-control[readonly] {
            background-color: #f8f9fa;
        }
    </style>
</head>
<body>
<div class="container mt-5">
    <h3 class="bg-primary text-white text-center p-3 rounded">Create New Bill</h3>

    <form method="post" action="addBill" onsubmit="updateItemsField()">
        <div class="mb-3">
            <label for="customer" class="form-label">Select Customer</label>
            <select class="form-select" name="customer" required>
                <option value="">-- Choose Customer --</option>
                <%
                    List<customer> customers = (List<customer>) request.getAttribute("customers");
                    if (customers != null) {
                        for (customer c : customers) {
                %>
                    <option value="<%= c.getAccountNumber() %>">
                        <%= c.getAccountNumber() %> - <%= c.getName() %>
                    </option>
                <%
                        }
                    }
                %>
            </select>
        </div>

        <hr>

        <%
            List<book> books = (List<book>) request.getAttribute("books");
        %>

        <% for (int i = 1; i <= 4; i++) { %>
        <div class="row g-3 mb-3 align-items-end">
            <div class="col-md-4">
                <label class="form-label">Book <%= i %></label>
                <select class="form-select book-select" name="book<%= i %>" onchange="updatePrice(this)">
                    <option value="">-- Select Book --</option>
                    <% if (books != null) {
                        for (book b : books) { %>
                            <option value="<%= b.getId() %>" data-price="<%= b.getPrice() %>"><%= b.getTitle() %></option>
                    <% } } %>
                </select>
            </div>
            <div class="col-md-2">
                <label class="form-label">Qty</label>
                <input type="number" name="qty<%= i %>" min="0" class="form-control quantity-input" onchange="updateTotal(this)">
            </div>
            <div class="col-md-3">
                <label class="form-label">Unit Price</label>
                <input type="text" readonly class="form-control unit-price" name="unitPrice<%= i %>">
            </div>
            <div class="col-md-3">
                <label class="form-label">Total</label>
                <input type="text" readonly class="form-control book-total" name="bookTotal<%= i %>">
            </div>
        </div>
        <% } %>

        <input type="hidden" id="items" name="items" value="">

        <div class="row mt-4">
            <div class="col-md-6 offset-md-6">
                <label class="form-label">Grand Total (LKR)</label>
                <input type="text" id="grandTotal" name="grandTotal" class="form-control text-end fw-bold bg-light" readonly>
                <input type="hidden" id="grandTotal" name="user" value="<%=username%>">
            </div>
        </div>

        <div class="mt-4 text-center">
            <button type="submit" class="btn btn-success">Generate Bill</button>
        </div>
    </form>
</div>

<script>
function formatNumber(num) {
    return parseFloat(num).toLocaleString('en-US', { minimumFractionDigits: 2, maximumFractionDigits: 2 });
}

function updatePrice(select) {
    const price = select.options[select.selectedIndex].getAttribute('data-price');
    const row = select.closest('.row');
    const unitPriceInput = row.querySelector('.unit-price');
    const qtyInput = row.querySelector('.quantity-input');
    const totalInput = row.querySelector('.book-total');

    if (price) {
        unitPriceInput.value = formatNumber(price);
        updateTotal(qtyInput);
    } else {
        unitPriceInput.value = '';
        totalInput.value = '';
        updateGrandTotal();
    }
}

function updateTotal(qtyInput) {
    const row = qtyInput.closest('.row');
    const unitPriceStr = row.querySelector('.unit-price').value.replace(/,/g, '');
    const unitPrice = parseFloat(unitPriceStr) || 0;
    const quantity = parseInt(qtyInput.value) || 0;
    const total = unitPrice * quantity;
    row.querySelector('.book-total').value = formatNumber(total);
    updateGrandTotal();
}

function updateGrandTotal() {
    let grandTotal = 0;
    document.querySelectorAll('.book-total').forEach(input => {
        const val = input.value.replace(/,/g, '');
        grandTotal += parseFloat(val) || 0;
    });
    document.getElementById('grandTotal').value = formatNumber(grandTotal);
}

function updateItemsField() {
    let selectedBooks = [];
    document.querySelectorAll('.book-select').forEach(select => {
        const selectedOption = select.options[select.selectedIndex];
        if (selectedOption && selectedOption.value !== "") {
            selectedBooks.push(selectedOption.text);
        }
    });
    document.getElementById('items').value = selectedBooks.join(', ');
}
</script>

</body>
</html>
