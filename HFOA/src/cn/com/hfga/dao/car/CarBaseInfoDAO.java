package cn.com.hfga.dao.car;
import java.util.List;


import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import cn.com.hfga.dao.common.SpringDataDAO;
import cn.com.hfga.entity.car.CarBaseInfoEntity;

@Repository("carBaseInfoDAO")
public interface CarBaseInfoDAO extends SpringDataDAO<CarBaseInfoEntity>{

	@Query(nativeQuery=true,value="select * from B_CarBaseInfo ")
	public List<CarBaseInfoEntity> findAll();
	
	@Query(nativeQuery=true,value="select * from B_CarBaseInfo where CarNum=:carnum ")
	public List<CarBaseInfoEntity> getBeginLength(@Param(value="carnum")String num);
	
	@Modifying
	@Query(nativeQuery=true,value="update B_CarBaseInfo set CarDistance=:cardistance where CarNum=:carnum")
	public int modifyLen(@Param(value="cardistance") String cardistance,@Param(value="carnum") String carnum);
	
	@Modifying
	@Query(nativeQuery=true,value="update B_CarBaseInfo set CarState=:carstate where CarNum=:carnum")
	public int modifyState(@Param(value="carstate")String carstate,@Param(value="carnum")String carnum);
	
	@Query(nativeQuery=true,value="select * from B_CarBaseInfo where CarNum=:carnum ")
	public List<CarBaseInfoEntity> getOneInfo(@Param(value="carnum") String carnum);

	@Query(nativeQuery=true,value="SELECT CONCAT(CarType,' ',CarNum) FROM b_carbaseinfo ORDER BY ID")
	//SELECT ID,CONCAT(CarType,' ',CarNum) FROM b_carbaseinfo ORDER BY ID
	public List<Object> getCarKind();
	
	//web·ÖÒ³
	@Query(nativeQuery = true, value = "select * from B_CarBaseInfo order by ID desc limit ?1,?2 ")
	public List<CarBaseInfoEntity> carDisplay(int start, int number);
	
	//webÌõÊý
	@Query(nativeQuery = true, value = "select count(*) from B_CarBaseInfo ")
	public int getAllCount();
	
	@Modifying
	@Query(nativeQuery=true,value="update B_CarBaseInfo set CarCode=:CarCode,CarNum=:CarNum,CarType=:CarType,"
			+ "CarUnit=:CarUnit,CarDetailInfo=:CarDetailInfo,"
			+ "SuspendDay=:SuspendDay,PeasonNum=:PeasonNum,"
			+ "CardVale=:CardVale,CarDistance=:CarDistance,"
			+ "CarInsuranceInfoDetal=:CarInsuranceInfoDetal,CarState=:CarState where ID=:ID")
	public int updateBaseInfo(@Param(value="CarCode") String CarCode,
			@Param(value="CarType") String CarType,
			@Param(value="CarUnit") String CarUnit,
			@Param(value="CarDetailInfo") String CarDetailInfo,
			@Param(value="CardVale") String CardVale,
			@Param(value="CarDistance") String CarDistance,
			@Param(value="CarInsuranceInfoDetal") String CarInsuranceInfoDetal,
			@Param(value="CarState") String CarState,
			@Param(value="CarNum") String CarNum,
			@Param(value="SuspendDay") String SuspendDay,
			@Param(value="PeasonNum") String PeasonNum,
			@Param(value="ID") String ID);
	
	@Modifying
	@Query(nativeQuery=true,value="insert into B_CarBaseInfo (ID,CarCode,CarNum,CarType,"
			+ "CarUnit,CarDetailInfo,SuspendDay,PeasonNum,CardVale,CarDistance,CarInsuranceInfoDetal,CarState) values (:ID,:CarCode,:CarNum,:CarType,"
			+ ":CarUnit,:CarDetailInfo,:SuspendDay,:PeasonNum,:CardVale,:CarDistance,:CarInsuranceInfoDetal,:CarState)")
	public int insertBaseInfo(@Param(value="CarCode") String CarCode,
			@Param(value="CarType") String CarType,
			@Param(value="CarUnit") String CarUnit,
			@Param(value="CarDetailInfo") String CarDetailInfo,
			@Param(value="CardVale") String CardVale,
			@Param(value="CarDistance") String CarDistance,
			@Param(value="CarInsuranceInfoDetal") String CarInsuranceInfoDetal,
			@Param(value="CarState") String CarState,
			@Param(value="CarNum") String CarNum,
			@Param(value="SuspendDay") String SuspendDay,
			@Param(value="PeasonNum") String PeasonNum,
			@Param(value="ID") String ID);
	
	@Query(nativeQuery=true,value="select max(ID) as ApplyId from B_CarBaseInfo ")
	public int getMaxId();
}
