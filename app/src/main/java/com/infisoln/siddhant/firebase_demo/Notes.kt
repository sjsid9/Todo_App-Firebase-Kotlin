package com.infisoln.siddhant.firebase_demo

class Notes {

    var title: String? = null
    var subtitle: String? = null

    constructor() {

    }

    constructor(title: String, subtitle: String) {
        this.title = title
        this.subtitle = subtitle
    }
}
