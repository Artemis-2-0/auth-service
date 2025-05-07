db.profile.insertOne({
    "firstName": "John",
    "middleName": "P",
    "lastName": "Doe",
    "gender": "MALE",
    "dateOfBirth": new Date("1984-04-12"),
    "addresses":[
        {
           "addressType": "RESIDENTIAL",
           "addressLine1": "13365 Batten Lane",
           "addressLine2": "Apt 234",
           "city": "Odessa",
           "state": "FL",
           "zipCode": "33556"
        }
    ],
    "phones": [
        {
            "phoneType": "HOME",
            "phoneNumber": "813-357-9153"
        }
    ],
    "emails": [
        {
            "emailType": "PERSONAL",
            "emailAddress": "john.doe@gmail.com"
        }
    ]
});
db.users.insertOne({
    "username": "john.doe@gmail.com",
    "password": "$2a$12$.JOeREsYXYUd7nb9T3GbWeyKz1cNfyAKTi4z6OgWKabDNv0QF8d5q",
    "accountNotExpired": true,
    "accountNotLocked": true,
    "credentialsNotExpired": true,
    "enabled": true,
    "profile": ObjectId("67f07beff448b8ae3ec191b7"),
    "roles":[
        {
           "roleName": "ADMIN",
           "authorities": [
               {
                   "permission": "user.create"
               },
               {
                   "permission": "user.read"
               },
               {
                   "permission": "user.update"
               },
               {
                   "permission": "user.delete"
               },
               {
                   "permission": "role.create"
               },
               {
                   "permission": "role.read"
               },
               {
                   "permission": "role.update"
               },
               {
                   "permission": "role.delete"
               },
               {
                   "permission": "authority.create"
               },
               {
                   "permission": "authority.read"
               },
               {
                   "permission": "authority.update"
               },
               {
                   "permission": "authority.delete"
               },
               {
                   "permission": "account.create"
               },
               {
                   "permission": "account.read"
               },
               {
                   "permission": "account.update"
               },
               {
                   "permission": "account.delete"
               }
           ]
        }
    ]
});

db.resource.insertOne({
  resourceName: "Get Accounts",
  resourceDescription: "Get all accounts in the system",
  resourceType: "ACCOUNT",
  resourceUri: "/api/v1/artemis/account/secured/get-accounts",
  resourceOwner: "ACCOUNT-MANAGEMENT",
  authorities: [
    {
      permission: "account.read"
    }
  ]
});

db.resource.insertOne({
  resourceName: "Create Account",
  resourceDescription: "Create an Account in the system",
  resourceType: "ACCOUNT",
  resourceUri: "/api/v1/artemis/account/secured/create-account",
  resourceOwner: "ACCOUNT-MANAGEMENT",
  authorities: [
    {
      permission: "account.create"
    }
  ]
});


