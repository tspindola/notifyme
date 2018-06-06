package com.example.tspindola.notifyme

import com.google.firebase.database.IgnoreExtraProperties


@IgnoreExtraProperties
class Person {
    var firstName: String? = ""
    var lastName: String? = ""
    var location: String? = ""
    var email: String? = ""
    var telephone: Long = 0
    var notificationType: Int = 0
    var uuid:String = ""

    constructor(){
    }

    constructor(uuid:String, fn:String?, ln:String?,l:String?,e:String?,t:Long,nt:Int){
        this.uuid = uuid
        this.firstName = fn
        this.lastName = ln
        this.location = l
        this.email = e
        this.telephone = t
        this.notificationType = nt
    }
}