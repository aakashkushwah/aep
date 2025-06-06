{{/*
Expand the name of the chart.
*/}}
{{- define "alert-service.name" -}}
alert-service
{{- end -}}

{{/*
Create a default fully qualified app name.
*/}}
{{- define "alert-service.fullname" -}}
{{- printf "%s-%s" .Release.Name (include "alert-service.name" .) | trunc 63 | trimSuffix "-" -}}
{{- end -}}

