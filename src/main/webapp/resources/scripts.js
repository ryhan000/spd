/* Find any element which has a 'data-onload' function and load that to simulate an onload. */
$('[data-onload]').each(function() {
	eval($(this).data('onload'));
});

$(document).ready(function() {
	$("#logout").click(function(e) {
		e.preventDefault();
		$("#logout-form").submit();
	});
});


$(function() {
	$("#date").datepicker({
		format : "dd-mm-yyyy",
		weekStart : 1,
		clearBtn : true,
		calendarWeeks : true,
		autoclose : true,
		todayHighlight : true
	});
});

function goBack() {
		window.history.back()
	}

function goToAddress(url) {
	window.location.href = url;
}

// <== Specification/edit.jsp ==>
function calcSpecificationSum(configuringTarif, programmingTarif,
		architectingTarif) {
	var finalLabel = document.getElementById('sum');
	var configuringHours = +document.getElementById('configuringHours').value;
	var programmingHours = +document.getElementById('programmingHours').value;
	var architectingHours = +document.getElementById('architectingHours').value;
	var hours = configuringHours + programmingHours + architectingHours;
	var days = +hours / 8;
	var specSum = (configuringHours * configuringTarif)
			+ (programmingHours * programmingTarif)
			+ (architectingHours * architectingTarif);
	finalLabel.innerHTML = '<b>' + specSum.toFixed(2) + " грн | "
			+ hours.toFixed(0) + " ч. | " + days.toFixed(0) + " дн." + '</b>';
}

// <== Calculation/edit.jsp ==>

function clearValue(inputId) {
	var input = document.getElementById(inputId);
	input.value = "";
	calcBankCostSum();
}

function getInitialValueBack(inputId, initialValue) {
	var input = document.getElementById(inputId);
	input.value = accounting.formatNumber(initialValue, 2, " ", ",");
	calcBankCostSum();
}

function formatValueAndCalculate(inputId) {
	var input = document.getElementById(inputId);
	var value = getNum(input.value);
	input.value = accounting.formatNumber(value, 2, " ", ",");
	calcBankCostSum();
}

function surchargeUpdate() {
	var surcharge = document.getElementById('surcharge');
	var salaryRate = document.getElementById('salaryRate');
	var premium = document.getElementById('premium');
	var openingBalance = document.getElementById('openingBalance');
	var closingBalance = document.getElementById('closingBalance');
	var moneyOnHand = document.getElementById('moneyOnHand');
	var turnover = document.getElementById('turnover');
	clearValue('surcharge');
	calculationUpdate();
	surcharge.value = Math.floor(getNum(salaryRate.value)
			+ getNum(premium.value) + getNum(openingBalance.value)
			- getNum(moneyOnHand.value));
	calculationUpdate();
	var turnoverValue = getNum(turnover.value);
	var temp = Math.floor(turnoverValue / 10) * 10;
	while (temp < turnoverValue) {
		temp += 10;
	}
	surcharge.value = (temp - turnoverValue) + getNum(surcharge.value);
	calculationUpdate();
	while (getNum(closingBalance.value) < 0) {
		surcharge.value = getNum(surcharge.value) + 10;
		calculationUpdate();
	}
	while (getNum(closingBalance.value) > 20) {
		surcharge.value = getNum(surcharge.value) - 10;
		calculationUpdate();
	}
	surcharge.value = accounting.formatNumber(getNum(surcharge.value), 2, "",
			",");
}

function calculationUpdate() {
	var salaryRate = document.getElementById('salaryRate');
	var premium = document.getElementById('premium');
	var esv = document.getElementById('esv');
	var rent = document.getElementById('rent');
	var surcharge = document.getElementById('surcharge');
	var withdrawCashComission = document
			.getElementById('withdrawCashComission');
	var openingBalance = document.getElementById('openingBalance');
	var closingBalance = document.getElementById('closingBalance');

	var simpleTax = document.getElementById('simpleTax');
	var cardServiceFee = document.getElementById('cardServiceFee');
	var accountServiceFee = document.getElementById('accountServiceFee');

	var turnover = document.getElementById('turnover');
	var moneyOnHand = document.getElementById('moneyOnHand');
	var moneyTransfer = document.getElementById('moneyTransfer');
	var withdrawCash = document.getElementById('withdrawCash');

	var comissionRate = document.getElementById('bankComissionRate').value;
	var simpleTaxRate = document.getElementById('simpleTaxRate').value;

	withdrawCash.value = accounting.formatNumber(
			(getNum(salaryRate.value) + getNum(premium.value)), 2, " ", ",");

	withdrawCashComission.value = accounting.formatNumber(
			(getNum(withdrawCash.value) * getNum(comissionRate)), 2, " ", ",");
	turnover.value = accounting
			.formatNumber(
					(getNum(withdrawCash.value) + getNum(cardServiceFee.value)
							+ getNum(accountServiceFee.value)
							+ getNum(esv.value) + getNum(surcharge.value)
							+ getNum(rent.value) + getNum(withdrawCashComission.value)),
					2, " ", ",");
	simpleTax.value = accounting.formatNumber(
			(getNum(turnover.value) * getNum(simpleTaxRate)), 2, " ", ",");
	moneyOnHand.value = accounting
			.formatNumber(
					(getNum(turnover.value) - (getNum(simpleTax.value)
							+ getNum(esv.value) + getNum(rent.value)
							+ getNum(cardServiceFee.value)
							+ getNum(accountServiceFee.value) + getNum(withdrawCashComission.value))),
					2, " ", ",");
	closingBalance.value = accounting
			.formatNumber((getNum(openingBalance.value)
					+ getNum(moneyOnHand.value) - getNum(withdrawCash.value)),
					2, " ", ",");

	moneyTransfer.value = accounting.formatNumber(
			(getNum(withdrawCash.value) + getNum(withdrawCashComission.value)),
			2, " ", ",");
	calcBankCostSum();
}

function getWithdrawCashComission(comissionRate) {
	// TODO
}

function calcBankCostSum() {
	var bankCost = document.getElementById('bankCost');
	var withdrawCashComission = document
			.getElementById('withdrawCashComission').value;
	var cardServiceFee = document.getElementById('cardServiceFee').value;
	var accountServiceFee = document.getElementById('accountServiceFee').value;
	bankCost.value = accounting.formatNumber((getNum(withdrawCashComission)
			+ getNum(cardServiceFee) + getNum(accountServiceFee)), 2, " ", ",")
			+ ' грн';
}

function getNum(val) {
	val = val.toString().replace(/\s+/g, '');
	val = val.toString().replace(/[,]/, ".");
	if (isNaN(val)) {
		return +0;
	}
	return +val;
}
