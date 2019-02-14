package api.arq.seguranca.jwt;

import api.model.Usuario;
import api.repository.usuario.UsuarioRepository;
import api.arq.seguranca.identificacao.GerenciadorAuthorities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;

import java.io.IOException;
import java.util.*;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;

/**
 * Created by Gustavo Galvao on 23/07/2018.
 */

public class JwtTokenFilter extends OncePerRequestFilter{

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private GerenciadorAuthorities gerenciadorAuthorities;

    @Override
    public void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain)  throws IOException, ServletException {
        Optional<String> headerToken = getTokenString(request.getHeader("Authorization"));
        if (headerToken.isPresent()) {
            String token = headerToken.get();
            Optional<Long> id = jwtService.getIdFromToken(token);
            if (existeAutenticacoesContexto()) {
                Usuario usuario = usuarioRepository.findById(id);
                switch (jwtService.verificaTempoExpirado(token)) {
                    case ANTES_DO_REFRESH:
                        setAuthentication(usuario, request);
                        break;
                    case DEPOIS_DA_EXPIRACAO:
                        setAuthentication(usuario, request);
                        break;
                    case DEPOIS_DO_REFRESH:
                        setAuthentication(usuario, request);
                        usuario.setToken(jwtService.toToken(usuario));
                        response.setHeader("Authorization", usuario.getToken());
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
        Collection<GrantedAuthority> authorities = gerenciadorAuthorities.getAuthorities(usuario);
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(usuario, null, authorities);
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
