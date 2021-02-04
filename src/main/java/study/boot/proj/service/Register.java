package study.boot.proj.service;

import study.boot.proj.dto.Member;

public interface Register {

    public int InsertMemberInfo(Member member);
    public Member SearchMemberInfo(Member member);
    public int UpdateMemberInfo(Member member);
    public int DeleteMemberInfo(int seq);

    public String SearchEmail(String email);
    public String SearchWithdrawal(int seq);

}
