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
  brands: BrandEntity[];
  shifters: ShifterEntity[];
}

interface IProps
{}

enum FieldTypes
{ 
  /*
  KEY = VALUE
  vigyazzunk arra, hogy a ket nev teljessen megegyezzen
  */
  cikkszam = "cikkszam",
  marka = "marka",
  vazmeret = "vazmeret",
  felniAtmero="felniAtmero",
  valtoTipus = "valtoTipus",
  tipus="tipus",
  ar="ar",
  url="kep"
}

class AddProduct extends Connected<typeof React.Component, IProps & WithStyles<typeof styles> & RouteComponentProps<{}>, IState, AppStore>(React.Component)
{ 
    constructor(props: IProps & WithStyles<typeof styles> & RouteComponentProps<{}>)
    {
      super(props);

      this.state =
      {
        brands: [],
        shifters: []
      }
    }

    componentWillMount = async (): Promise<void> =>
    {
      let brands: {id: number | undefined, name: string | undefined }[] = await WebAPI.Brand.all().then(x => x);
      let shifters: {id: number | undefined, name: string | undefined }[] = await WebAPI.Shifter.all().then(x => x);

      this.setState
      ({
        ...this.state,
        brands,
        shifters 
      });
    }

    private form : React.RefObject<Form> = React.createRef<Form>();

    submit = async (): Promise<void> =>
    {
      const data = {...this.form.current!.state!.values};

      const bicycle :BicycleEntity =
      {
        Ar: data[FieldTypes.ar],
        Cikkszam: data[FieldTypes.cikkszam],
        FelniAtmeroID: data[FieldTypes.felniAtmero],
        Id: 0,
        MarkaID: data[FieldTypes.marka],
        TipusID: data[FieldTypes.tipus],
        URL: data[FieldTypes.url],
        ValtoTipus: data[FieldTypes.valtoTipus],
        VazmeretID: data[FieldTypes.vazmeret],
      }
     
      WebAPI.Bicycle.create(bicycle)
                    .then( (response: BicycleEntity) =>
                    {
                      //TODO: navigate to admin page
                      //this.props.history.push();
                    })
                    .catch( (error: Error) =>
                    {
                      //TODO: navigete to error page
                      //this.props.history.push();
                    })
    }

    render()
    {
        const css = this.props.classes;

		/*a mezok nevekne meg kell eggyezni a FieldTypes megadott nevekkel*/
        const fields: IFields =
        {
          cikkszam:
          {
            id: FieldTypes.cikkszam,
            label: "Cikszám",
            validation: [ {rule: required} ]
          },
          marka:
          {
            id: FieldTypes.marka,
            label: "Márka",
            editor: "dropdown",
            selectData: this.state ? this.state.brands : [],
            validation: [ {rule: required} ]
          },
          vazmeret:
          {
            id: FieldTypes.vazmeret,
            label: "Váz méret",
            editor: "dropdown",
            selectData: []
          },
          felniAtmero:
          {
            id: FieldTypes.felniAtmero,
            label: "Felni átmérő",
            editor: "dropdown",
            selectData: []
          },
          valtoTipus:
          {
            id: FieldTypes.valtoTipus,
            label: "Váltó típus",
            editor: "dropdown",
            selectData: this.state ? this.state.shifters : [],
            validation: [ {rule: required} ]
          },
          tipus:
          {
            id: FieldTypes.tipus,
            label: "Típus",
            editor: "dropdown",
            selectData: []
          },
          ar:
          {
            id: FieldTypes.ar,
            label: "Ár",
            validation: [ {rule: minValue, args: 0}, {rule: required}]
          },
          url:
          {
            id: FieldTypes.url,
            label: "Kép",
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
                            Új kerékpár adatainak felvitele:
                        </div>
                        <Field {...fields.cikkszam} />
                        <Field {...fields.marka} />
                        <Field {...fields.vazmeret} />
                        <Field {...fields.felniAtmero} />
                        <Field {...fields.valtoTipus} />
                        <Field {...fields.tipus} />
                        <Field {...fields.ar} />
                        <Field {...fields.url} />
                    </React.Fragment>
                )}
            />
            <FooterComponent />
        </React.Fragment>

        return Body();
    }
}

const AddProductPage = withRoot(withStyles(styles)(AddProduct));
export default AddProductPage;
