// import axios from 'axios'

// const URI = 'http://localhost:8080';

// class RegistrationService {
//     async register(firstname, lastname, email, password, confirmedPassword) {
//         const data = {
//             firstname,
//             lastname,
//             email,
//             password,
//             confirmedPassword
//         }
//         try {
//             const response = await axios.post(URI + '/auth/register', JSON.stringify(data), {
//                 headers: {
//                     'Content-Type': 'application/json'
//                 }
//             })

//             localStorage.setItem("token", response.data.data.token);

//             return response.data
//         } catch (error) {
//             throw new Error(`Registration failed: ${error.message}`)
//         }
//     }
// }

// export default new RegistrationService()
