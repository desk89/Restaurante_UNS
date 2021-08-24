package com.example.restauranteuns

data class Pedido(var cantidad: String ?= null,var cliente:String ?= null,var nroTracking: Long ?=null,var plato:String ?= null,var total: Long?= null)
