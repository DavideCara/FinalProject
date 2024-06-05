const r_username = document.getElementById('r-username')
const r_email = document.getElementById('r-email')
const r_password1 = document.getElementById('r-password1')
const r_password2 = document.getElementById('r-password2')
const email_error = document.getElementById('check-email')
const submit = document.getElementById('r_button')

window.addEventListener('load', () => {
  const validated_form = false
  // timer x email(guarda r_email.addEventListener)
  let emailTypingTimer
  const emailDoneTypingInterval = 1000

  let isRegisterPasswordValidated = false

  // SOLO per controllare l'email
  function validateEmail(email) {
    let pattern =
      /^[a-zA-Z0-9.!#$%&’*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/

    if (!pattern.test(email)) {
      sessionStorage.setItem('email_error', 'Email non valida')
      console.log('email non valida')
      return false
    }
    return true
  }

  // per inserire scritta "email non valida"
  function displayError() {
    let error = sessionStorage.getItem('email_error')
    if (error) {
      email_error.innerHTML = "<span style='color: red;'>" + error + '</span>'
      sessionStorage.removeItem('email_error')
    } else {
      email_error.innerHTML = ''
    }
  }

  // Validate email in real-time
  r_email.addEventListener('input', () => {
    //timer per non controllare ad ogni lettera scritta
    //ma dopo aver scritto l'email completa
    clearTimeout(emailTypingTimer)

    emailTypingTimer = setTimeout(() => {
      validateEmail(r_email.value)
      displayError()
    }, emailDoneTypingInterval)
  })
  displayError()
})
