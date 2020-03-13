import * as React from "react";
import { Connected } from "./../../../../lib/store/connected.mixin";
import { RouteComponentProps, Route } from "react-router";
import { AppStore } from "./../../../../lib/appStore";
import { Theme, createStyles, withStyles, WithStyles } from "@material-ui/core"
import withRoot from "./../../../../withRoot";
import { CustomColors } from "./../../../../style/colors";
import HeaderComponent from "./../../../../pages/header/header";
import FooterComponent from "./../../../../pages/footer/footer";
import { BicycleEntity } from "./../../../../services/client/bicycleService";
import { WebAPI } from "./../../../../services/webAPI";
import { Form } from "./../../../../components/Form/Form";
import { Field } from "./../../../../components/Form/component/Field";
import { IFields } from "./../../../../components/Form/interfaces/IFields";
import { required } from "./../../../../components/Form/validators/required";
import { BrandEntity } from "./../../../../services/client/brandService";
import { ShifterEntity } from "./../../../../services/client/shifterService";
import { minValue } from "./../../../../components/Form/validators/minValue";
import { Urls } from "./../../../../routing/urls";

const styles = (theme: Theme) =>
  createStyles
  ({
    container:
    {
      display: "flex",
      flexDirection: "column",
      flexGrow: 1,
      justifyContent: "center",
      alignItems: "center",
      minHeight: "100vh",
      backgroundColor: CustomColors.background
    },
    hText:
    {
      color: "#33ff00"
    },
    textField:
    {
      width: "90%"
    },
    textFieldLabel:
    {
      color : `${CustomColors.darkerFont} !important`,
    },
    textFieldOutlinedInput:
    {
      "&$cssFocused $notchedOutline":
      {
        borderColor: `${CustomColors.darkerFont} !important`,
      }
    },
    textFieldFocused:
    {
      color: "orange !important"
    },
    textFieldNotchedOutline:
    {
      borderWidth: "1px",
      borderColor: CustomColors.darkerFont + "!important"
    }
  })

interface IState
{
  shifters: ShifterEntity[];
  shifter: ShifterEntity;
}

interface IProps
{}

enum FieldTypes
{ 
  /*
  KEY = VALUE
  vigyazzunk arra, hogy a ket nev teljessen megegyezzen
  */
  valtoTipus = "valtoTipus",
  valtoUj = "valtoUj"
}

class AddShifter extends Connected<typeof React.Component, IProps & WithStyles<typeof styles> & RouteComponentProps<{}>, IState, AppStore>(React.Component)
{ 
    constructor(props: IProps & WithStyles<typeof styles> & RouteComponentProps<{}>)
    {
      super(props);

      this.state =
      {
        ...this.state,
        shifters: []
      }
    }

    componentWillMount = async (): Promise<void> =>
    {
      let shifters: {id: number | undefined, name: string | undefined }[] = await WebAPI.Shifter.all().then(x => x);

      this.setState
      ({
        ...this.state,
        shifters 
      });
    }

    private form : React.RefObject<Form> = React.createRef<Form>();

    submit = async (): Promise<void> =>
    {
      const data = {...this.form.current!.state!.values};

      const shifter: ShifterEntity = data[FieldTypes.valtoUj];

      console.log(shifter);

      WebAPI.Shifter.shifterPost(shifter).catch().then(x => x);
    }

    render()
    {
        const css = this.props.classes;

		/*a mezok nevekne meg kell eggyezni a FieldTypes megadott nevekkel*/
        const fields: IFields =
        {
          valtoTipus:
          {
            id: FieldTypes.valtoTipus,
            label: "Váltó típus",
            editor: "dropdown",
            selectData: this.state ? this.state.shifters : []
          },
          newShifter:
          {
            id: FieldTypes.valtoUj,
            label: "Új váltó típus: ",
            validation: [{rule: required}]
          }
        }

        const Body = () =>
        <React.Fragment>
            <Route render={ props => <HeaderComponent {...props}/> }/>
            <Form
                ref={this.form}
                submit={() => this.submit()}
                fields={ fields }
                render={() => 
                (
                    <React.Fragment>
                        <div className="alert alert-info" role="alert">
                            Új váltó típus felvitele:
                        </div>
                        <Field {...fields.valtoTipus} />
                        <Field {...fields.newShifter} />
                    </React.Fragment>
                )}
            />
            <FooterComponent />
        </React.Fragment>

        return Body();
    }
}

const AddShifterPage = withRoot(withStyles(styles)(AddShifter));
export default AddShifterPage;
