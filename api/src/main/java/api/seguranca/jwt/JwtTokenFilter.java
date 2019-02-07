package api.seguranca.jwt;

import api.model.Usuario;
import api.repository.UsuarioRepository;
import  api.util.SituacaoToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.Authentication;
import  org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.*;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Gustavo Galvao on 23/07/2018.
 */

public class JwtTokenFilter{

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private JwtService jwtService;

    public void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain)  throws IOException, ServletException {
        Optional<String> headerToken = getTokenString(request.getHeader("Authorization"));
        if (headerToken.isPresent()) {
            String token = headerToken.get();
            Optional<Long> id = jwtService.getIdFromToken(token);
            if (existeAutenticacoesContexto()) {
                switch (jwtService.verificaTempoExpirado(token)) {
                    case ANTES_DO_REFRESH:
                        Usuario usuarioA = usuarioRepository.findByIdUser(id);
                        setAuthentication(usuarioA, request);
                        break;
                    case DEPOIS_DA_EXPIRACAO:
                        Usuario usuarioB = usuarioRepository.findByIdUser(id);
                        setAuthentication(usuarioB, request);
                        break;
                    case DEPOIS_DO_REFRESH:
                        Usuario usuarioC = usuarioRepository.findByIdUser(id);
                        setAuthentication(usuarioC, request);
                        usuarioC.setToken(jwtService.toToken(usuarioC));
                        response.setHeader("Authorization", usuarioC.getToken());
                        break;
                    default: break;
                }
            }

        }
        filterChain.doFilter(request, response);
    }

    private Boolean existeAutenticacoesContexto(){
        return SecurityContextHolder.getContext().getAuthentication() == null;
    }

    private void setAuthentication(Usuario usuario, HttpServletRequest request) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(usuario, null);
        token.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(token);
    }

    private Optional<String> getTokenString(String header){
        if(header == null){
            return Optional.empty();
        }
        else{
            return Optional.ofNullable(header);
        }
    }
}
