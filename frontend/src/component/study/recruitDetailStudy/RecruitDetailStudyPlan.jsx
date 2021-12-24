import React, { PureComponent } from "react";
import style from "../../../css/study/study_make_complete/study_make_complete.module.css";
import Collapse from "react-collapse-transition";
import { FaAngleUp } from "react-icons/fa";
import { FaAngleDown } from "react-icons/fa";
import RecruitDetailStudyTextArea from "./RecruitDetailStudyTextArea";
import RecruitDetailStudyPlanLine from "./RecruitDetailStudyPlanLine";

class RecruitDetailStudyPlan extends PureComponent {
  constructor(props) {
    super(props);
    this.state = {
      visible: false,
      icon: false,
      listNum: 3,
      list: [],
    };
    this.toggle = this.toggle.bind(this);
    this.addLine = this.addLine.bind(this);
    this.removeLine = this.removeLine.bind(this);
  }
  toggle = () => {
    if (this.state.visible === true) {
      this.setState({ visible: false, icon: false });
    } else {
      this.setState({ visible: true, icon: true });
    }
  };
    addLine = () => {
        const { list } = this.state;
        const newList = <RecruitDetailStudyPlanLine key={this.state.listNum}
            times={this.state.listNum} />
        this.setState({
            list: [...list, newList],
            listNum: this.state.listNum + 1,
        })
  
    }
    removeLine = () => {
        if (this.state.listNum == 3) {
            return;
        }
        const { list } = this.state;
        this.setState({
          list: [...list.slice(0,this.state.list.length-1)],
          listNum: this.state.listNum - 1,
        });
  };
    render() {
    return (
      <React.Fragment>
        <div className={style.applicant_wrap}>
          <div className={style.applicant} onClick={this.toggle}>
            스터디 계획
            <span className={style.applicant_icon}>
              {this.state.icon ? <FaAngleUp /> : <FaAngleDown />}
            </span>
          </div>
          <Collapse visible={this.state.visible}>
            <div className={style.plan_wrap}>
              <form>
                <div className={style.plan_title}>
                  <div className={style.plan_h3}>스터디 규칙</div>
                </div>
                <RecruitDetailStudyTextArea
                  name="study-rule"
                  width="95%"
                  height="130px"
                />
                <div className={style.schedule_wrap}>
                  <div className={style.plan_title}>
                    <div className={style.plan_h3}>주차별 세부 학습계획</div>
                  </div>
                  <table className={style.schedule_table}>
                    <thead>
                      <tr>
                        <th className={style.schedule_times}>회차</th>
                        <th className={style.schedule_content}>학습내용</th>
                        <th className={style.schedule_method}>학습방법</th>
                        <th className={style.schedule_date}>활동일자</th>
                      </tr>
                    </thead>
                    <tbody>
                      <RecruitDetailStudyPlanLine times={1} />
                      <RecruitDetailStudyPlanLine times={2} />
                      {this.state.list}
                    </tbody>
                  </table>

                  <div className={style.schedule_line_wrap}>
                    <div
                      className={style.schedule_line_add}
                      onClick={this.addLine}
                    >
                      추가
                    </div>
                    <div
                      className={style.schedule_line_remove}
                      onClick={this.removeLine}
                    >
                      삭제
                    </div>
                  </div>
                  <div className={style.plan_save_wrap}>
                    <button className={style.plan_save} type="button">저장</button>
                  </div>
                </div>
              </form>
            </div>
          </Collapse>
        </div>
      </React.Fragment>
    );
  }
}

export default RecruitDetailStudyPlan;
