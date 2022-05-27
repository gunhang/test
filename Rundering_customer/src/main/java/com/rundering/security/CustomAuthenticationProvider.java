package com.rundering.security;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;

import com.rundering.dto.MemberVO;
import com.rundering.service.MemberService;
import com.rundering.util.UserSha256;


public class CustomAuthenticationProvider implements AuthenticationProvider {
   
   private MemberService memberService;
   public void setMemberService(MemberService memberService) {
      this.memberService = memberService;
   }
    
 
   @Override 
   public Authentication authenticate(Authentication auth) throws AuthenticationException {
      
      String parameter_id = (String) auth.getPrincipal(); // 로그인 시도한 ID를 가져온다
       String []split_id = parameter_id.split(":==:");
       String login_id=split_id[0];
       String login_check =null;
       if(split_id.length==2) login_check=split_id[1];
       
      String login_pwd = (String) auth.getCredentials(); //로그인 시도한 Password 를 가져온다.
      String pw = UserSha256.encrypt(login_pwd);
       MemberVO member = null;
       
       try {
          member=memberService.getMember(login_id);
      } catch (Exception e) {
         e.printStackTrace();
         throw new BadCredentialsException("서버 장애로 서비스가 불가합니다.");
      }
       
       
       if(member != null) {
          if(member.getLoginfailCount()>4) {
             if(login_check==null) throw new BadCredentialsException("redirect");
               if(!login_check.equals("true")) throw new BadCredentialsException("redirect");
          }
           
              
          
          if(pw.equals(member.getPassword())) {//아이디 패스워드 일치
             UserDetails authUser = new User(member,memberService);
             boolean invalidCheck = authUser.isAccountNonExpired()
                       && authUser.isAccountNonLocked()
                       && authUser.isCredentialsNonExpired()
                       && authUser.isEnabled();
             if(invalidCheck) {
                //    스프링 시큐리티 내부 클래스로 인증 토큰 생성한다.
                UsernamePasswordAuthenticationToken result =
                     new UsernamePasswordAuthenticationToken(authUser.getUsername(),
                                                    authUser.getPassword(),
                                                   authUser.getAuthorities());
                // 전달할 내용을 설정한 후 
                  result.setDetails(authUser);
                  // 리턴한다. successHandler로 전송된다. 
                  try {
                  memberService.loginSuccess(member.getMemberNo());
               } catch (Exception e) {
                   e.printStackTrace();
                   throw new BadCredentialsException("서버 장애로 서비스가 불가합니다.");
               }
                  
                  return result;
             }
             
             throw new BadCredentialsException("상태변경으로 로그인이 불가합니다.");
          }else { // 패스워드 불일치
             
             
            try { 
                memberService.loginFailIncrease(member.getMemberNo()); 
            } catch(Exception e) { 
                e.printStackTrace();
                throw new BadCredentialsException("서버 장애로 서비스가 불가합니다."); 
            }
            
            if(member.getLoginfailCount()>4) throw new BadCredentialsException("redirect");
             throw new BadCredentialsException("패스워드가 일치하지 않습니다.");
          }
       }else { // 존재하지 않는 아이디
          throw new BadCredentialsException("존재하지 않는 아이디입니다.");
       }      
   
   }

   @Override
   public boolean supports(Class<?> auth) {
      return auth.equals(UsernamePasswordAuthenticationToken.class);
   }

}




