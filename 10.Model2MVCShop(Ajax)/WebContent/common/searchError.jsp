<%@ page contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>

<html>
	<body>
	
		<h3>�˻� ������ �߸� �Ǿ����ϴ�.</h3>
		�˻��� ���� : ${search.searchKeyword }<br/>
		�˻��� ���� : ${search.searchCondition == 0? "��ǰ��ȣ":"" }
		${search.searchCondition == 1? "��ǰ��":"" }
		${search.searchCondition == 2? "����<br/> ��Ȯ�� ������ �Է��ϼž��մϴ�.":"" }
	</body>
</html>