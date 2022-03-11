package com.tapestry
package framework.primitives

import company.ServiceType

/**
 * An internal reference to a service
 *
 * @param service   the service logic
 * @param runtime   the service compute
 * @param inType    the input
 * @param outType   the output
 * @param propsType the output
 * @tparam S the service
 * @tparam T the runtime
 * @tparam I the input Type
 * @tparam O the output Type
 * @tparam P the props Type
 */
case class ServiceRef[S >: Service[_, _, _], T >: Construct[_, _, _], I, O, P]
(service: S, runtime: T, inType: I, outType: O, propsType: P, serviceType: ServiceType)
