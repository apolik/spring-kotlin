package org.polik.springkotlin.exception

/**
 * Created by Polik on 7/9/2022
 */
class NotFoundException : RuntimeException {
    constructor() : super()
    constructor(message: String?) : super(message)
}
