{{/*
Expand the name of the chart.
*/}}
{{- define "notification-service.name" -}}
notification-service
{{- end -}}

{{/*
Create a default fully qualified app name.
*/}}
{{- define "notification-service.fullname" -}}
{{- printf "%s-%s" .Release.Name (include "notification-service.name" .) | trunc 63 | trimSuffix "-" -}}
{{- end -}}

