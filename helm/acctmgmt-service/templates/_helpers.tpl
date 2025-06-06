{{/*
Expand the name of the chart.
*/}}
{{- define "acctmgmt-service.name" -}}
acctmgmt-service
{{- end -}}

{{/*
Create a default fully qualified app name.
*/}}
{{- define "acctmgmt-service.fullname" -}}
{{- printf "%s-%s" .Release.Name (include "acctmgmt-service.name" .) | trunc 63 | trimSuffix "-" -}}
{{- end -}}

