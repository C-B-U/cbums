import React, { PureComponent } from 'react';
import style from "../../../css/study/study_make_complete/study_make_complete.module.css";
import Collapse from 'react-collapse-transition';
import { FaAngleUp } from "react-icons/fa";
import { FaAngleDown } from "react-icons/fa";
class RecruitDetailStudyHeadCount extends PureComponent {
  constructor(props) {
    super(props);
    this.state = {
      visible: false,
      icon: false,
    };
    this.toggle = this.toggle.bind(this);
  }
  toggle = () => {
    if (this.state.visible === true) {this.setState({ visible: false, icon: false});
    } else {this.setState({visible: true, icon:true,});}}


  render() {
    return (
      <React.Fragment>
        <div className={style["main__studyComplete-number-wrap"]}>
          <form>
            <button className={style["main__studyComplete-number-apply"]}>
              신청
            </button>
            {/*!--신청 버튼--*/}
          </form>
          <div className={style.number_wrap}>
            <div className={style["main__studyComplete-number"]}>
              모집 인원: 0/4
            </div>
            {/*--인원수--*/}
          </div>
          <div className={style.applicant_wrap}>
            <div className={style.applicant} onClick={this.toggle}>
              신청자 목록
              <span className={style.applicant_icon}>
                {this.state.icon ?  <FaAngleUp />  : <FaAngleDown />}
              </span>
            </div>
            {/* if: 개설자일때 */}
            <Collapse visible={this.state.visible}>
              <div className={style["openedStudy__openTable"]}>
                <table className={style["openedStudy__table"]}>
                  <thead>
                    <tr>
                      <th
                        className={style["openedStudy__table--name"]}
                      >
                        이름
                      </th>
                      <th className={style["openedStudy__table--clubNum"]}>
                        기수
                      </th>
                      <th className={style["openedStudy__table--applyDate"]}>
                        신청 날짜
                      </th>
                      <th className={style["openedStudy__table--phoneNum"]}>
                        연락처
                      </th>
                      <th className={style["openedStudy__table--phoneNum"]}>
                        수락/거절
                      </th>
                    </tr>
                  </thead>
                  <tbody>
                    <tr>
                      <th>최지미</th>
                      <td>7기</td>
                      <td>21/07/08</td>
                      <td>010-0000-0000</td>
                      <td>
                        <button className={style.accept}>수락</button>
                        <button className={style.reject}>거절</button>
                      </td>
                    </tr>
                    <tr>
                      <th>ㅇㅡㅇ</th>
                      <td>6기</td>
                      <td>21/07/08</td>
                      <td>010-0000-0000</td>
                      <td>
                        <button className={style.accept}>수락</button>
                        <button className={style.reject}>거절</button>
                      </td>
                    </tr>
                    <tr>
                      <th>ㅇㅅㅇ</th>
                      <td>5기</td>
                      <td>21/07/08</td>
                      <td>010-0000-0000</td>
                      <td>
                        <button className={style.accept}>수락</button>
                        <button className={style.reject}>거절</button>
                      </td>
                    </tr>
                  </tbody>
                </table>
              </div>
            </Collapse>
          </div>
          <div className={style.partition} />
        </div>
      </React.Fragment>
    );
  }
}

export default RecruitDetailStudyHeadCount;