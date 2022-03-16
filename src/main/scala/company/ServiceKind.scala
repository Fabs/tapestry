package com.tapestry
package company

sealed trait ServiceKind
case object Container extends ServiceKind
case object Lambda extends ServiceKind
