# wicket-quarkus-cdi

Wicket CDI - Integration für Quarkus.

In Quarkus wird die CDI Spezifikation durch Arc (https://github.com/quarkusio/quarkus/tree/main/independent-projects/arc) implementiert. Jedoch ist
die Implementierung nicht vollständig (https://quarkus.io/guides/cdi-reference#limitations).
Somit funktioniert die Wicket CDI Integration (https://github.com/apache/wicket/tree/master/wicket-cdi) nicht im Zusammenspiel mit Quarkus, da in ihr
auf BeanManager Methoden zugegriffen wird, die in der BeanManager Implementierung von Arc nicht vorhanden sind.

Ziel dieses Showcases ist es DependencyInjection via der _javax.inject.Inject_ Annotation in Pages und Components zu ermöglichen. Hierzu wurde die
dafür relevante Wicket-CDI Funktionalität nachprogrammiert. Der Zugriff auf das jeweilige Injection-Field erfolgt via Reflection.

## Start

Konsole: mvnw quarkus:dev

Url: http://localhost:8080/showcase

## Integration
### WicketFilter

Mangels web.xml wird der WicketFilter über die javax.servlet.annotation.WebFilter Annotation konfiguriert.

### Wicket Application

Die Verwendung der Wicket Quarkus CDI Funktionalität erfolgt analog zur Wicket CDI Integration über die Application Klasse.

```
@Override
protected void init() {
	super.init();
	new CdiConfiguration().configure(this);
}
```

## Limitierungen

Es ist nur möglich Injects in Components (Pages, Panels, ...) vorzunehmen. 

Weitere Features der Wicket CDI Integration müssten, sofern möglich, bei Bedarf implementiert werden.

Qualifier sind nicht umgesetzt.
