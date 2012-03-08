<%@ page import="com.acme.model.Order" %>



<div class="fieldcontain ${hasErrors(bean: orderInstance, field: 'amount', 'error')} required">
	<label for="amount">
		<g:message code="order.amount.label" default="Amount" />
		<span class="required-indicator">*</span>
	</label>
	<g:select name="amount" from="${1..15}" class="range" required="" value="${fieldValue(bean: orderInstance, field: 'amount')}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: orderInstance, field: 'name', 'error')} required">
	<label for="name">
		<g:message code="order.name.label" default="Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="name" required="" value="${orderInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: orderInstance, field: 'price', 'error')} required">
	<label for="price">
		<g:message code="order.price.label" default="Price" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="number" name="price" required="" value="${fieldValue(bean: orderInstance, field: 'price')}"/>
</div>

