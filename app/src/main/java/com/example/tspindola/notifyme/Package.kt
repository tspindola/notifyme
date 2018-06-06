package com.example.tspindola.notifyme

import com.google.firebase.database.IgnoreExtraProperties


@IgnoreExtraProperties
class Package {
    var uuid: String = ""
    var trackingNumber: String? = ""
    var recipient: String? = ""
    var location: String? = ""
    var sender: String? = ""
    var isurgent: Boolean = false
    var isfragile: Boolean = false
    var isheavy: Boolean = false
    var isdelivered: Boolean = false
    var observation: String? = ""

    constructor(){
    }

    constructor(uuid: String, trackingNumber: String?, recipient: String?, location: String?,
                sender: String?, isurgent: Boolean, isfragile: Boolean, isheavy: Boolean,
                isdelivered: Boolean, observation: String?) {
        this.uuid = uuid
        this.trackingNumber = trackingNumber
        this.recipient = recipient
        this.location = location
        this.sender = sender
        this.isurgent = isurgent
        this.isfragile = isfragile
        this.isheavy = isheavy
        this.isdelivered = isdelivered
        this.observation = observation
    }

}