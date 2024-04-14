
//allOpen{
//    annotation("javax.persistence.Entity")
//    annotation("javax.persistence.MappedSuperclass")
//    annotation("javax.persistence.Embeddable")
//}

dependencies {
    runtimeOnly("com.mysql:mysql-connector-j")
    compileOnly(project(":domain"))
}
