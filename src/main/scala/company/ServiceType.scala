package com.tapestry
package company

sealed trait ServiceType
case object Container extends ServiceType
case object Lambda extends ServiceType
