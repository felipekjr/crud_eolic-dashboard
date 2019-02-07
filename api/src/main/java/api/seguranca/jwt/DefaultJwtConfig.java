package api.seguranca.jwt;
import api.model.Usuario;
import api.util.SituacaoToken;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.util.*;

/**
 * Created by Gustavo Galvao on 23/07/2018.
 */

@Component
class DefaultJwtConfig implements JwtService {
        @Value("\\${jwt.secret}")
        private String secret = "";

        @Value("\\${jwt.sessionTime}")
        private int sessionTime = 0;

        @Value("\\${jwt.refreshTime}")
        private int refreshTime = 0;

        private static final long MILISEGUNDOS_POR_MINUTO = 60000;

        public String toToken(Usuario usuario){
                return Jwts
                .builder()
                .setSubject(usuario.getId().toString())
                .setExpiration(expireTimeFromNow())
                .signWith(SignatureAlgorithm.HS512,secret)
                .compact();
        }

        @Override
        public Optional<Long> getIdFromToken(String token){
                try {
                        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
                        return Optional.of(java.lang.Long.valueOf(claimsJws.getBody().getSubject()));
                }catch (Exception e) {
                        return Optional.empty();
                }
        }

        public SituacaoToken verificaTempoExpirado(String token){
                Jws<Claims> claimsJws = Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
                Date dataAtual = new Date(System.currentTimeMillis());
                Date dataExpiracao = claimsJws.getBody().getExpiration();
                Date dataRefresh = getHorarioRefresh(dataExpiracao, sessionTime, refreshTime);
                return identificaSituacaoToken(dataAtual, dataExpiracao, dataRefresh);
        }

        @Override
        public SituacaoToken identificaSituacaoToken(Date horaAtual, Date horarioExpiracao, Date horarioRefresh){
                Long horaAtualMili = horaAtual.getTime();
                Long horarioExpiracaoMili = horarioExpiracao.getTime();
                Long horarioRefreshMili = horarioRefresh.getTime();
                if(horarioRefreshMili > horaAtualMili){
                       return SituacaoToken.ANTES_DO_REFRESH;
                }else if(horaAtualMili >= horarioRefreshMili + 1 && horaAtualMili <= horarioExpiracaoMili - 1){
                        return SituacaoToken.DEPOIS_DO_REFRESH;
                }else{
                       return SituacaoToken.DEPOIS_DA_EXPIRACAO;
                }
        }

        @Override
        public Date getHorarioRefresh(Date horaExpiracao , int sessionTime, int refreshTime) {
                return new Date(horaExpiracao.getTime() + (refreshTime - sessionTime) * MILISEGUNDOS_POR_MINUTO);
        }

        private Date expireTimeFromNow() {
                return new Date(System.currentTimeMillis() + sessionTime * MILISEGUNDOS_POR_MINUTO);
        }
}
