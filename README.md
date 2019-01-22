# eolic-app
Aplicação composta por dois módulos: api e webapp.

# API  
Link: https://eolic-api.herokuapp.com/api/  
**endpoints abertos:** /usuarios:(POST, GET) e /login:(POST)  
**endpoints com JWT:** /aerogerador, /complexo_eolico, /parque_eolico

# WEBAPP
Link: https://eolic-webapp.herokuapp.com  
Para logar no WEBAPP, antes, cadastre um usuário.
Caso queira logar com um usuário já cadastrado, consulte o login e a senha na api. Copie a senha encriptada, como está na api.

# EDIT
O JWT não está implementado completamente no Front. Foi utilizado apenas um token padrão para logar o usuário a título de demonstração.


