<#if forms?size gt 0>
<#list forms as form>
CREATE FORM ${form.name} [
<#list form.inputs as input>
	${input.name} ${input.condition} (${input.args})
</#list>
]
</#list>
</#if>

<#if reports?size gt 0>
<#list reports as report>
CREATE VIEW ${report.name} FOR ${report.table} [
<#list report.inputs as input>
	${input.name} ${input.condition} ${input.args}
</#list>
]	
</#list>
</#if>