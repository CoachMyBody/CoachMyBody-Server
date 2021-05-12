package com.coachmybody.exercise.type;

import lombok.Getter;

@Getter
public enum MuscleType {
	TRAPEZIUS("승모근", "등세모근", "Trapezius"),
	DELTOID("삼각근", "어깨세모근", "Deltoid"),
	SERRATUS_ANTERIOR("전거근", "앞톱니근", "Serratus anterior"),
	PECTORALIS_MAJOR("대흉근", "큰 가슴근", "Pectoralis major"),
	PECTORALIS_MINOR("소흉근", "작은 가슴근", "Pectoralis minor"),
	LATISSIMUS_DORSI("광배근", "넓은등근", "Latissimus dorsi"),
	ERECTOR_SPINAE("기립근", "척추세움근", "Erector Spinae"),
	TERES_MAJOR("대원근", "큰원근", "Teres major"),
	TERES_MINOR("소원근", "작은원근", "Teres minor"),
	TRICEPS_BRACHII("상완삼두근", "위팔세갈래근", "Triceps brachii"),
	BICEPS_BRACHII("상완이두근", "위팔두갈래근", "Biceps brachii"),
	EXTERNAL_OBLIQUE("외복사근", "배바깥갈빗근", "External oblique"),
	INTERNAL_OBLIQUE("내복사근", "배속빗근", "Internal oblique"),
	RECTUS_ABDOMINIS("복직근", "배곧은근", "Rectus abdominis"),
	TRANSVERSUS_ABDOMINIS("복횡근", "배가로근", "Transversus abdominis"),
	GLUETEUS_MEDIUS("중둔근", "중간볼기근", "Glueteus medius"),
	GLUETEUS_MAXUMUS("대둔근", "큰볼기근", "Glueteus maxumus"),
	ILIOPSOAS("장요근", "엉덩허리근", "ILIOPSOAS"),
	QUADRATUS_LUMBORUM("요방형근", "허리네모근", "Quadratus lumborum"),
	TENSOR_FASCIAE_LATAE("대퇴근막장근", "넙다리근막긴장근", "Tensor fasciae latae"),
	ADDUCTOR("내전근", "모음근", "Adductor"),
	ABDUCTOR("외전근", "벌림근", "Abductor"),
	QUADRICEPS_FEMORIS("Quadriceps femoris", "대퇴사두근", "넙다리네갈래근"),
	HAMSTRINGS("햄스트링", "넙다리뒤근육", "Hamstrings"),
	TIBIALIS_ANTERIOR("전경골근", "앞정강근", "Tibialis anterior"),
	GASTROCNEMIUS("비복근", "장딴지근", "Gastrocnemius"),
	SOLEUS("넙치근", "가자미근", "Soleus");

	private String name;
	private String ko;
	private String eng;

	MuscleType(String name, String ko, String eng) {
		this.name = name;
		this.ko = ko;
		this.eng = eng;
	}
}
